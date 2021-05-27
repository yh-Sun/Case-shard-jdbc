package com.sun.forward.repository;

import com.sun.forward.entity.OrderRiskKeys;
import com.sun.forward.entity.PayRisk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 支付表-投保单信息
 *
 * @author  Sun
 * @date    2020/2/27 16:23
 * @since   1.0
 */
public interface PayRiskRepository extends JpaRepository<PayRisk, OrderRiskKeys> {

    List<PayRisk> findByOrderNo(String orderNo);

    @Transactional(rollbackFor = RuntimeException.class)
    @Modifying
    void deleteByOrderNo(String orderNo);

    PayRisk findByProposalNo(String proposalNo);

}
