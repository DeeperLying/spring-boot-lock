package com.evan.wj.service;

import com.alibaba.fastjson.JSON;
import com.evan.wj.dao.ArticleDao;
import com.evan.wj.dao.ArticleListDao;
import com.evan.wj.pojo.Article;
import com.evan.wj.pojo.ArticleList;
import com.evan.wj.utils.MyException;
import org.omg.CORBA.Any;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ArticleService {
    @Autowired
    ArticleDao articleDao;
    @Autowired
    ArticleListDao articleListDao;
    public int saveArticle(Article article) {
        // ArticleList articleList =  articleListDao.findbyArticleTitle(article.getTitle());
        Map<String, String> articleList = new HashMap<String,String>(articleDao.findByArticleTitle(article.getTitle()));
        if (articleList == null) {
            throw new MyException(412, "该文章没有写简介");
        }
        System.out.print(JSON.toJSON(articleList));
        // TODO 如果我想用Int类型去接收id再不改变Map<String, String>的情况下如何通过类型转换，拿到int id
        String result = String.valueOf(articleList.get("id"));
        String title = article.getTitle();
        String author = article.getAuthor();
        String date = article.getDate();
        String text = article.getText();
        String textleng = article.getTextleng();
        if (articleList != null) {
            // return articleDao.saveActicle(id, title, date, author, text, textleng);
        } else {
            return 0;
        }
        return 0;
    }
}
