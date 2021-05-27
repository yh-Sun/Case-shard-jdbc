package com.sun.forward.entity;

import lombok.Data;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * 订单_车险产品信息表
 *
 * @author  Sun
 * @date    2020/2/29 10:31
 * @since   1.0
 */
@Data
public class OrderRiskKeys implements Serializable {

    @Id
    private String orderNo;

    @Id
    private String productCode;

    public OrderRiskKeys() {
    }

    public OrderRiskKeys(String orderNo, String productCode) {
        this.orderNo = orderNo;
        this.productCode = productCode;
    }

}
