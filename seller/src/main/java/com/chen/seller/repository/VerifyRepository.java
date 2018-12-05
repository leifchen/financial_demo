package com.chen.seller.repository;

import com.chen.entity.VerificationOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * 对账 Repository
 *
 * @Author LeifChen
 * @Date 2018-12-03
 */
@Repository
public interface VerifyRepository extends JpaRepository<VerificationOrder, String>, JpaSpecificationExecutor<VerificationOrder> {

    /**
     * 查询某段时间[start,end)内的某个渠道channelId的对账数据
     *
     * @param channelId 渠道id
     * @param start     开始时间
     * @param end       结束时间
     * @return 对账数据列表
     */
    @Query(value = "SELECT CONCAT_WS('|', order_id, outer_order_id, channel_id, channel_user_id, product_id, order_type, amount, DATE_FORMAT(gmt_create, '%Y-%m-%d %H:%i:%s')) FROM t_order where order_status = 'success' and channel_id = ?1 and gmt_create >= ?2 and gmt_create < ?3", nativeQuery = true)
    List<String> queryVerificationOrders(String channelId, Date start, Date end);

    /**
     * 长款
     *
     * @param channelId
     * @param start
     * @param end
     * @return
     */
    @Query(value = "SELECT t.`outer_order_id` FROM t_order t LEFT JOIN verification_order v ON t.`channel_id` = ?1 AND t.`outer_order_id` = v.`order_id` WHERE v.`order_id` IS NULL AND t.gmt_create >= ?2 AND t.gmt_create < ?3", nativeQuery = true)
    List<String> queryExcessOrders(String channelId, Date start, Date end);

    /**
     * 漏单
     *
     * @param channelId
     * @param start
     * @param end
     * @return
     */
    @Query(value = "SELECT v.`order_id` FROM verification_order v LEFT JOIN t_order t ON t.`channel_id` = ?1 AND v.`outer_order_id` = t.`order_id` WHERE t.`order_id` IS NULL AND v.gmt_create >= ?2 AND v.gmt_create < ?3", nativeQuery = true)
    List<String> queryMissOrders(String channelId, Date start, Date end);

    /**
     * 不一致
     *
     * @param channelId
     * @param start
     * @param end
     * @return
     */
    @Query(value = "SELECT t.outer_order_id FROM t_order t JOIN verification_order v ON t.`channel_id` = ?1 AND t.`outer_order_id` = v.`order_id` WHERE CONCAT_WS('|',t.channel_id,t.channel_user_id,t.product_id,t.order_type,t.amount,DATE_FORMAT( t.gmt_create,'%Y-%m-%d %H:%i:%s')) != CONCAT_WS('|',v.channel_id,v.channel_user_id,v.product_id,v.order_type,v.amount,DATE_FORMAT( v.gmt_create,'%Y-%m-%d %H:%i:%s')) AND t.gmt_create >= ?2 AND t.gmt_create < ?3", nativeQuery = true)
    List<String> queryDifferentOrders(String channelId, Date start, Date end);
}
