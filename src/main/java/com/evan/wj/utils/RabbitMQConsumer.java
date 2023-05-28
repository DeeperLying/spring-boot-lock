package com.evan.wj.utils;

import com.evan.wj.dao.OrderListDao;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author SuperLee
 * @date 2023/5/28 上午12:53
 */
// TODO 不消费正常队列，走死信队列
//@Component
//@RabbitListener(queuesToDeclare = @Queue("RABBITMQ_DEMO_TOPIC_QUEUE"))
//public class RabbitMQConsumer {
//    @RabbitHandler
//    public void process(Map map) {
//        System.out.println(map.toString() + ":以消费");
//    }
//}

@Component
public class RabbitMQConsumer {
    @Autowired
    OrderListDao orderListDao;

    @RabbitListener(queuesToDeclare = @Queue("deal_queue"))
    public void process(String out_trade_no) {
        System.out.println(out_trade_no + ":一分钟后进入死信");
        String orderStatus =  orderListDao.getOrderStatus(out_trade_no);
        System.out.println(orderStatus + "orderStatus");
        System.out.println(orderStatus == "WAIT_BUYER_PAY");
        if ("WAIT_BUYER_PAY".equals(orderStatus)) { // 这样写吧不容报空指针
            orderListDao.updateGoodsOrderStatus(out_trade_no, "TRADE_CLOSED");
        } else {
            System.out.println("没进来？");
        }
    }
}

