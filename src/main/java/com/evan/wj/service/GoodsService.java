package com.evan.wj.service;

import com.evan.wj.dao.GoodsDao;
import com.evan.wj.pojo.GoodsPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

/**
 * @author SuperLee
 * @date 2023/5/7 下午2:31
 */
@Service
public class GoodsService {
    @Autowired
    GoodsDao goodsDao;

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
}
