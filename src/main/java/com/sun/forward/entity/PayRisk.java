package com.sun.forward.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 支付表-投保单信息
 *
 * @author  Sun
 * @date    2020/8/17 10:29
 * @since   1.0
 */
@Data
@Table
@Entity
@IdClass(OrderRiskKeys.class)
public class PayRisk implements Serializable {

    /** 订单号 */
    @Id
    private String orderNo;

    /** 产品代码 */
    @Id
    private String productCode;

    /** 投保单号 */
    private String proposalNo;

    /** 收据号 */
    private String uniqueNo;

    /** 保单号 */
    private String policyNo;

    /** 申请单状态（初始核保通过4；失败5，成功6） */
    private String payResult;

    /** 保单落地处理结果信息 */
    private String payResultMsg;

    /** 支付金额 */
    private String premium;

    /** 车船税：当年应缴 */
    private String taxActual;

}
