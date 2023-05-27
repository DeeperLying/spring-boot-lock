package com.evan.wj.utils;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
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
    @RabbitListener(queuesToDeclare = @Queue("deal_queue"))
    public void process(Map map) {
        System.out.println(map.toString() + ":一分钟后进入死信");
    }
}

