package com.chen.seller.repository;

import com.chen.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * 订单 Repository
 *
 * @Author LeifChen
 * @Date 2018-12-03
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, String>, JpaSpecificationExecutor {
}
