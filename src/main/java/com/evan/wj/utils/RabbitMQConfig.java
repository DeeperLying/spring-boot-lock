package com.evan.wj.utils;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;


/**
 * @author SuperLee
 * @date 2023/5/28 上午12:15
 */

@Configuration
public class RabbitMQConfig implements BeanPostProcessor {
    //这是创建交换机和队列用的rabbitAdmin对象
    @Resource
    private RabbitAdmin rabbitAdmin;

    //初始化rabbitAdmin对象
    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
        // 只有设置为 true，spring 才会加载 RabbitAdmin 这个类
        rabbitAdmin.setAutoStartup(true);
        return rabbitAdmin;
    }

    //实例化bean后，也就是Bean的后置处理器
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        //创建交换机
        rabbitAdmin.declareExchange(rabbitmqDemoDirectExchange());
        rabbitAdmin.declareExchange(dealExchange());
        //创建队列
        rabbitAdmin.declareQueue(rabbitmqDemoDirectQueue());
        rabbitAdmin.declareQueue(dealQueue());
        return null;
    }

    @Bean
    public Queue rabbitmqDemoDirectQueue() {
        // 队列
        // Map<String, Object> args = new HashMap<>(1);
        //args.put("x-message-ttl", 5 * 1000);
        Map<String, Object> args  = new HashMap<>();
        args.put("x-message-ttl", 60000);
        args.put("x-dead-letter-exchange", "deal_exchange");
        args.put("x-dead-letter-routing-key", "deal_queue_key");
        return new Queue("RABBITMQ_DEMO_TOPIC_QUEUE", false, false, false, args);
    }

    @Bean
    public DirectExchange rabbitmqDemoDirectExchange() {
        //Direct交换机
        return new DirectExchange("RABBITMQ_DEMO_DIRECT_EXCHANGE", true, false);
    }

    @Bean
    public Binding bindDirect() {
        //链式写法，绑定交换机和队列，并设置匹配键
        return BindingBuilder
                //绑定队列
                .bind(rabbitmqDemoDirectQueue())
                //到交换机
                .to(rabbitmqDemoDirectExchange())
                //并设置匹配键
                .with("RABBITMQ_DEMO_DIRECT_ROUTING_KEY");
    }

    // 创建死信交换机
    @Bean
    public Queue dealQueue() {
        return new Queue("deal_queue", true);
    }

    @Bean
    public DirectExchange dealExchange() {
        //Direct交换机
        return new DirectExchange("deal_exchange", true, false);
    }

    @Bean
    public Binding bindingDeadExchange() {
        //链式写法，绑定交换机和队列，并设置匹配键
        return BindingBuilder
                //绑定队列
                .bind(dealQueue())
                //到交换机
                .to(dealExchange())
                //并设置匹配键
                .with("deal_queue_key");
    }
}
