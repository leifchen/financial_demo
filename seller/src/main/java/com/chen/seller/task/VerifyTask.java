package com.chen.seller.task;

import com.chen.seller.enums.ChannelEnum;
import com.chen.seller.service.VerifyService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 定时对账任务
 *
 * @Author LeifChen
 * @Date 2018-12-05
 */
@Component
public class VerifyTask {

    @Resource
    private VerifyService verifyService;

    /**
     * 生成对账文件
     */
    @Scheduled(cron = "0 0 1,3,5 * * ? ")
    public void makeVerificationFile() {
        Date yesterday = new Date(System.currentTimeMillis() - 24 * 60 * 60 * 1000);
        for (ChannelEnum channelEnum : ChannelEnum.values()) {
            verifyService.makeVerificationFile(channelEnum.getChannelId(), yesterday);
        }
    }

    /**
     * 对账
     */
    @Scheduled(cron = "0 0 2,4,6 * * ? ")
    public void verify() {
        Date yesterday = new Date(System.currentTimeMillis() - 24 * 60 * 60 * 1000);
        for (ChannelEnum channelEnum : ChannelEnum.values()) {
            verifyService.verifyOrder(channelEnum.getChannelId(), yesterday);
        }
    }
}
