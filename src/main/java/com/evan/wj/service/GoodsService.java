package com.evan.wj.service;

import com.evan.wj.dao.GoodsDao;
import com.evan.wj.pojo.GoodsPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
