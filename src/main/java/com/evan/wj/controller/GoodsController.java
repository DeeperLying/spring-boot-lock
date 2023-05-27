package com.evan.wj.controller;

import com.evan.wj.pojo.GoodsPojo;
import com.evan.wj.pojo.OrderListPojo;
import com.evan.wj.result.Result;
import com.evan.wj.service.GoodsService;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author SuperLee
 * @date 2023/5/7 上午11:28
 */
@RestController
@CrossOrigin(allowCredentials = "true")
@RequestMapping("/api")
public class GoodsController {
    @Autowired
    GoodsService goodsService;

    // 我目前对@Autowired and @Resource 的理解是规范是 @Autowired 是用来调用自己写的@Bean @Resource是用来调用Maven的
    @Resource
    private RabbitTemplate rabbitTemplate;

    @PostMapping(value = "/createGoods")
    public Result createGoods(@RequestBody GoodsPojo goodsPojo) {
//       String out_trade_no =  UUID.randomUUID().toString();
//       System.out.println(out_trade_no.replaceAll("-",""));
        System.out.println(goodsPojo);
       int isSave =  goodsService.createGoods(goodsPojo);
       System.out.println(isSave + "=======>>");
       if (isSave == 1) {
           return new Result(200, "success");
       } else {
           return new Result(400, "inter goods failed");
       }

    }

    @GetMapping(value = "/getGoodsList")
    public Result getGoodsList() {
       List<GoodsPojo> goodsList = goodsService.getGoodsList();
       if (!goodsList.isEmpty()) {
           Map resultBody = new HashMap(1);
           resultBody.put("goodsList", goodsList);
           return new Result(200, resultBody);
       } else {
           return new Result(400, "find goods failed");
       }
    }

    @GetMapping(value = "/getOrderList")
    public Result getOrderList() {
        List<OrderListPojo> orderList = goodsService.getOrderList();
        if (!orderList.isEmpty()) {
            Map resultBody = new HashMap(1);
            resultBody.put("orderList", orderList);
            return new Result(200, resultBody);
        } else {
            return new Result(400, "find order failed");
        }
    }

    @PostMapping(value = "/rabbitMQ")
    public Result createRabbitMQ () {
        //日期格式化
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String msgId = UUID.randomUUID().toString().replace("-", "").substring(0, 32);
        String sendTime = sdf.format(new Date());
        Map<String, Object> map = new HashMap<>();
        map.put("msgId", msgId);
        map.put("sendTime", sendTime);
        map.put("msg", "随便谢谢");
        rabbitTemplate.convertAndSend("RABBITMQ_DEMO_DIRECT_EXCHANGE", "RABBITMQ_DEMO_DIRECT_ROUTING_KEY", map);
        return new Result(200, "success");
    }
}
