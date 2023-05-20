package com.evan.wj.controller;

import com.evan.wj.pojo.GoodsPojo;
import com.evan.wj.pojo.OrderListPojo;
import com.evan.wj.result.Result;
import com.evan.wj.service.GoodsService;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
}
