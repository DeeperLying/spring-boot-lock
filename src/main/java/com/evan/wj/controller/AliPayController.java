package com.evan.wj.controller;

import com.evan.wj.dao.OrderListDao;
import com.evan.wj.pojo.GoodsPojo;
import com.evan.wj.result.Result;
import com.evan.wj.service.AliPayService;
import com.evan.wj.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Set;

/**
 * @author SuperLee
 * @date 2023/4/5 上午11:41
 */

@RestController
@CrossOrigin(allowCredentials = "true")
@RequestMapping("/api")
public class AliPayController {

    @Autowired
    AliPayService aliPayService;

    @Autowired
    GoodsService goodsService;

    @Autowired
    OrderListDao orderListDao;

//    @PostMapping(value = "/comeHerePay")
//    public Result comeHereAliPay(@RequestBody Map data, HttpServletResponse httpServletResponse) {
//        System.out.println(data + "source");
//
//        try {
//            Object body =  aliPayService.aliPay();
//            return new Result(200, "success", body);
//        } catch (Exception e) {
//           System.out.println(e+"errorMessage");
//            return new Result(500, e.getMessage());
//        }
//
//    }

    @GetMapping(value = "/comeHerePay")
    public Result comeHereAliPay(@RequestParam(value = "id", required = true) int id, HttpServletResponse response) {
        System.out.println(id);
        response.setContentType("text/html;charset=UTF-8");
          Map goods =  goodsService.getGoods(id);
          if (!goods.isEmpty()) {
              try {
                  Object body =  aliPayService.aliPay(goods);
                  return new Result(200, "success", body);
              } catch (Exception e) {
                  System.out.println(e+"errorMessage");
                  return new Result(500, e.getMessage());
              }
          } else {
              return new Result(400, "find goods failed");
          }
    }

    @PostMapping(value = "/aliPayAsyncNotify")
    public void aliPayNotify(HttpServletRequest request) {
        Map<String,String[]> map = request.getParameterMap();
        Set<String> keys = map.keySet();

        for(String key : keys) {
            String[] value = map.get(key);
            System.out.println(key + " = " + value[0]);
        }

        System.out.println(map.get("out_trade_no")[0] + "进来了，订单号");
        String out_trade_no = map.get("out_trade_no")[0];
        String trade_status = map.get("trade_status")[0];
        System.out.println(trade_status + "trade_status");

        orderListDao.updateGoodsOrderStatus(out_trade_no, trade_status);
    }
}
