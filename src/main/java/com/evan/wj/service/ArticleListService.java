package com.evan.wj.service;

import com.evan.wj.dao.ArticleListDao;
import com.evan.wj.pojo.ArticleList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleListService {
    @Autowired
    ArticleListDao articleListdao;

    public List<ArticleList> getArticleList() {
        return articleListdao.findAll();
    }
}
