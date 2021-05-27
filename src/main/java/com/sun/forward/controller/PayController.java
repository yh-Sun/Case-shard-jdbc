package com.sun.forward.controller;

import com.sun.forward.entity.PayRisk;
import com.sun.forward.repository.PayRiskRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class PayController {

    private final PayRiskRepository payRiskRepository;

    public PayController(PayRiskRepository payRiskRepository) {
        this.payRiskRepository = payRiskRepository;
    }

    @GetMapping("save")
    public void save(String orderNo) {
        PayRisk risk = new PayRisk();
        risk.setOrderNo(orderNo);
        risk.setPayResult("2");
        risk.setProductCode("0044");
        risk.setPremium(UUID.randomUUID().toString());
        risk.setTaxActual("哈哈哈哈哈");
        payRiskRepository.save(risk);
    }

    @GetMapping("del")
    public void del(String orderNo) {
        payRiskRepository.deleteByOrderNo(orderNo);
    }

    @GetMapping("info")
    public void info(String orderNo) {
        List<PayRisk> byOrderNo = payRiskRepository.findByOrderNo(orderNo);
        System.out.println(byOrderNo);
    }

}
