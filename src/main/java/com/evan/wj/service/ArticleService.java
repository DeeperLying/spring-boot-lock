package com.evan.wj.service;

import com.alibaba.fastjson.JSON;
import com.evan.wj.dao.ArticleDao;
import com.evan.wj.dao.ArticleListDao;
import com.evan.wj.pojo.Article;
import com.evan.wj.pojo.ArticleList;
import com.evan.wj.utils.MyException;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ArticleService {
    @Autowired
    ArticleDao articleDao;
    @Autowired
    ArticleListDao articleListDao;
    public int saveArticle(Article article) {
        String title = article.getTitle();
        String author = article.getAuthor();
        Date date = article.getDate();
        String text = article.getText();
        String introduction = article.getIntroduction();
            return articleDao.saveActicle(title, date, author, text, introduction);
    }

    public Article getArticle(int id) {
       Article article  = articleDao.findByArticle(id);
       System.out.println(JSON.toJSON(article));
       return article;
    }

    public List<Map> findByArticleList(int startIndex, int overIndex) {
        List<Article> articleList =  articleDao.findByArticleList(startIndex, overIndex);
        List<Map> result = new ArrayList<>();
        for (Article article: articleList) {
            Map item = new HashMap<String,Object>();
            item.put("id", article.getId());
            item.put("author", article.getAuthor());
            item.put("date", article.getDate());
            item.put("title", article.getTitle());
            item.put("introduction", article.getIntroduction());
            result.add(item);
        }
        return result;
    }
}
