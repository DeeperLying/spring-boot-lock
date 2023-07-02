package com.evan.wj.service;

import com.alibaba.fastjson.JSON;
import com.evan.wj.dao.ArticleDao;
import com.evan.wj.dao.ArticleListDao;
import com.evan.wj.pojo.Article;
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
        String text = article.getText();
        String introduction = article.getIntroduction();
        String text_html = article.getText_html();
        String banner = article.getBanner();
        int user_id =article.getUserId();
        return articleDao.saveActicle(title, author, text, introduction, text_html, user_id, banner);
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
            item.put("title", article.getTitle());
            item.put("introduction", article.getIntroduction());
            item.put("text_html", article.getText_html());
            item.put("user_id", article.getUserId());
            item.put("create_time", article.getCreate_time());
            item.put("banner", article.getBanner());
            result.add(item);
        }
        return result;
    }

    public List<Map> findByUserArticleList(int startIndex, int overIndex, int userId) {
        List<Map> articleList =  articleDao.findByUserArticleList(userId, startIndex, overIndex);
        return articleList;
    }
}
