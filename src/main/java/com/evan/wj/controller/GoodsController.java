package com.evan.wj.controller;

import com.evan.wj.pojo.GoodsPojo;
import com.evan.wj.result.Result;
import com.evan.wj.service.GoodsService;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
       return new Result(200, "success");
    }
}
