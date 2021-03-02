package com.gupaoedu.service.impl;

import com.gupaoedu.service.OrderService;
import com.gupaoedu.service.PayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderServiceImpl  implements OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    PayService payService;

//    @Transactional
    @Override
    public void placeOrder(String userId) {
        logger.info("准备执行 payService.pay()方法");
        payService.pay(userId);
    }

    @Override
    public void placeOrder1() {
        //payService.pay();
    }

    @Override
    public void placeOrder2(String userId) {
        payService.pay2(userId);
    }

    @Override
    public void placeOrder3() {
        payService.pay3();
    }

    @Override
    public void placeOrder4() {
        payService.pay4();
    }
}