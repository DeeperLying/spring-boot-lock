package com.evan.wj.service;

import com.evan.wj.dao.ArticleDao;
import com.evan.wj.pojo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {
    @Autowired
    ArticleDao articleDao;
    public int saveArticle(Article article) {
        String title = article.getTitle();
        String author = article.getAuthor();
        String date = article.getDate();
        String text = article.getText();
        String textleng = article.getTextleng();
        return articleDao.saveActicle(title, date, author, text, textleng);
    }
}
