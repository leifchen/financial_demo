package com.chen.seller.service;

import com.chen.entity.VerificationOrder;
import com.chen.seller.enums.ChannelEnum;
import com.chen.seller.repository.VerifyRepository;
import org.aspectj.util.FileUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 对账 Service
 *
 * @Author LeifChen
 * @Date 2018-12-04
 */
@Service
public class VerifyService {

    private static DateFormat DAY_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private static DateFormat DAYTIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    private static String END_LINE = System.getProperty("line.separator", "\n");

    @Value("${verification.rootdir:/opt/verification}")
    private String rootDir;

    private final VerifyRepository verifyRepository;

    public VerifyService(VerifyRepository verifyRepository) {
        this.verifyRepository = verifyRepository;
    }

    /**
     * 生成某个渠道某天的对账文件
     *
     * @param channelId 渠道id
     * @param day       某天
     * @return 对账文件
     */
    public File makeVerificationFile(String channelId, Date day) {
        File path = getPath(rootDir, channelId, day);
        if (path.exists()) {
            return path;
        }
        try {
            path.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 构造起止时间
        Date start = getStartOfDay(day);
        Date end = add24Hours(start);
        List<String> orders = verifyRepository.queryVerificationOrders(channelId, start, end);
        String content = String.join(END_LINE, orders);
        FileUtil.writeAsString(path, content);
        return path;
    }

    /**
     * 构造开始时间
     *
     * @param day
     * @return
     */
    private Date getStartOfDay(Date day) {
        String startStr = DAY_FORMAT.format(day);
        Date start = null;
        try {
            start = DAY_FORMAT.parse(startStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return start;
    }

    /**
     * 增加24小时
     *
     * @param start
     * @return
     */
    private Date add24Hours(Date start) {
        return new Date(start.getTime() + 1000 * 60 * 60 * 24);
    }

    /**
     * 保存渠道订单数据
     *
     * @param channelId 渠道id
     * @param day       时间
     */
    public void saveChannelOrders(String channelId, Date day) {
        ChannelEnum conf = ChannelEnum.getByChannelId(channelId);
        // 根据配置从ftp下载对账数据
        File file = getPath(conf.getRootDir(), channelId, day);
        if (!file.exists()) {
            return;
        }
        String content = null;
        try {
            content = FileUtil.readAsString(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] lines = content.split(END_LINE);
        List<VerificationOrder> orders = new ArrayList<>();
        for (String line : lines) {
            orders.add(parseLine(line));
        }
        verifyRepository.saveAll(orders);
    }

    public List<String> verifyOrder(String channelId, Date day) {
        List<String> errors = new ArrayList<>();
        // 构造起止时间
        Date start = getStartOfDay(day);
        Date end = add24Hours(start);
        List<String> excessOrders = verifyRepository.queryExcessOrders(channelId, start, end);
        List<String> missOrders = verifyRepository.queryMissOrders(channelId, start, end);
        List<String> differentOrders = verifyRepository.queryDifferentOrders(channelId, start, end);

        errors.add("长款订单号：" + String.join(",", excessOrders));
        errors.add("漏单订单号：" + String.join(",", missOrders));
        errors.add("不一致订单号：" + String.join(",", differentOrders));
        return errors;
    }

    /**
     * 获取对账文件路径
     *
     * @param rootDir   根路径
     * @param channelId 渠道id
     * @param day       时间
     * @return 对账文件路径
     */
    private File getPath(String rootDir, String channelId, Date day) {
        String name = DAY_FORMAT.format(day) + "-" + channelId + ".txt";
        File path = Paths.get(rootDir, name).toFile();
        return path;
    }

    /**
     * 按照顺序解析字符串创建对账订单
     * order_id, outer_order_id, channel_id, channel_user_id, product_id, order_type, amount, gmt_create
     *
     * @param line
     * @return 对账单
     */
    private static VerificationOrder parseLine(String line) {
        VerificationOrder order = new VerificationOrder();
        String[] props = line.split("\\|");
        order.setOrderId(props[0]);
        order.setOuterOrderId(props[1]);
        order.setChannelId(props[2]);
        order.setChannelUserId(props[3]);
        order.setProductId(props[4]);
        order.setOrderType(props[5]);
        order.setAmount(new BigDecimal(props[6]));
        try {
            order.setGmtCreate(DAYTIME_FORMAT.parse(props[7]));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return order;
    }
}
