package com.evan.wj.service;

import com.alibaba.fastjson.JSONObject;
import com.evan.wj.dao.GoodsDao;
import com.evan.wj.dao.OrderListDao;
import com.evan.wj.pojo.GoodsPojo;
import com.evan.wj.pojo.OrderListPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author SuperLee
 * @date 2023/5/7 下午2:31
 */
@Service
public class GoodsService {
    @Autowired
    GoodsDao goodsDao;

    @Autowired
    OrderListDao orderListDao;

    public int createGoods(GoodsPojo goodsPojo) {
       int isSave =  goodsDao.saveGoods(goodsPojo.getGoods_title(), goodsPojo.getGoods_image(),goodsPojo.getSex(),goodsPojo.getSize(),goodsPojo.getGoods_desc(),goodsPojo.getSale());
       return isSave;
    }

    public List<GoodsPojo> getGoodsList() {
       List<GoodsPojo> goodsList = goodsDao.getGoodsList();
       return goodsList;
    }

    public Map getGoods(int id) {
        Map goods = goodsDao.getGoods(id);
        return goods;
    }

    public int createGoodsOrder(Map goods, String out_trade_no) {
        int isSave = orderListDao.saveGoodsOrder(goods.get("id").toString(), out_trade_no, goods.get("goods_title").toString(), goods.get("goods_image").toString(),
                Integer.parseInt(goods.get("sex").toString()), goods.get("size").toString(),goods.get("goods_desc").toString(),Integer.parseInt(goods.get("sale").toString()), "WAIT_BUYER_PAY");
        return isSave;
    }

    public List<OrderListPojo> getOrderList() {
        List<OrderListPojo> goodsList = orderListDao.getOrderList();
        return goodsList;
    }
}
