package com.chen.manager.service;

import com.chen.entity.Product;
import com.chen.enums.ProductStatus;
import com.chen.manager.error.ErrorEnum;
import com.chen.manager.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 产品 Service
 *
 * @Author LeifChen
 * @Date 2018-10-01
 */
@Slf4j
@Service
public class ProductService {

    @Resource
    private ProductRepository productRepository;

    /**
     * 添加产品
     *
     * @param product
     * @return
     */
    public Product saveProduct(Product product) {
        log.debug("创建产品，参数:{}", product);
        // 数据校验
        checkProduct(product);
        // 设置默认值
        setDefault(product);
        Product result = productRepository.save(product);
        log.debug("创建产品，结果={}", result);
        return result;
    }

    /**
     * 查询单个产品
     *
     * @param id 产品编号
     * @return 返回对应产品或 null
     */
    public Product getOne(String id) {
        Assert.notNull(id, "需要产品编号参数");
        log.debug("查询单个产品，id={}", id);
        Product result = productRepository.findById(id).orElse(null);
        log.debug("查询单个产品，结果={}", result);
        return result;
    }

    /**
     * 分页查询产品
     *
     * @param idList
     * @param minRewardRate
     * @param maxRewardRate
     * @param statusList
     * @param pageable
     * @return
     */
    public Page<Product> query(List<String> idList,
                               BigDecimal minRewardRate,
                               BigDecimal maxRewardRate,
                               List<String> statusList,
                               Pageable pageable) {

        log.debug("查询产品，idList={}，minRewardRate={}，maxRewardRate={}，statusList={}，pageable={}", idList, minRewardRate, maxRewardRate, statusList, pageable);
        Specification<Product> specification = (Specification<Product>) (root, query, cb) -> {
            Expression<String> idCol = root.get("id");
            Expression<BigDecimal> rewardRateCol = root.get("rewardRate");
            Expression<String> statusCol = root.get("status");
            List<Predicate> predicates = new ArrayList<>();
            if (idList != null && idList.size() > 0) {
                predicates.add(idCol.in(idList));
            }
            if (minRewardRate != null && BigDecimal.ZERO.compareTo(minRewardRate) < 0) {
                predicates.add(cb.ge(rewardRateCol, minRewardRate));
            }
            if (maxRewardRate != null && BigDecimal.ZERO.compareTo(maxRewardRate) < 0) {
                predicates.add(cb.le(rewardRateCol, maxRewardRate));
            }
            if (statusList != null && statusList.size() > 0) {
                predicates.add(statusCol.in(statusList));
            }

            query.where(predicates.toArray(new Predicate[0]));
            return null;
        };

        Page<Product> result = productRepository.findAll(specification, pageable);
        log.debug("查询产品，结果={}", result);
        return result;
    }

    /**
     * 产品数据校验
     * 1. 非空数据
     * 2. 收益率要在 0-30 以内
     * 3. 投资步长需为整数
     *
     * @param product
     */
    private void checkProduct(Product product) {
        Assert.notNull(product.getId(), ErrorEnum.ID_NOT_NULL.getCode());
        Assert.isTrue(BigDecimal.ZERO.compareTo(product.getRewardRate()) < 0 && BigDecimal.valueOf(30).compareTo(product.getRewardRate()) >= 0, ErrorEnum.ILLEGAL_REWARD_RATE_RANGE.getCode());
        Assert.isTrue(BigDecimal.valueOf(product.getStepAmount().longValue()).compareTo(product.getStepAmount()) == 0, ErrorEnum.ILLEGAL_STEP_AMOUNT.getCode());
    }

    /**
     * 设置默认值
     * 1. 创建时间、更新时间
     * 2. 投资步长、锁定期
     *
     * @param product
     */
    private void setDefault(Product product) {
        if (product.getGmtCreate() == null) {
            product.setGmtCreate(new Date());
        }
        if (product.getGmtModified() == null) {
            product.setGmtModified(new Date());
        }
        if (product.getStepAmount() == null) {
            product.setStepAmount(BigDecimal.ZERO);
        }
        if (product.getStatus() == null) {
            product.setStatus(ProductStatus.AUDITING.name());
        }
    }
}
