package com.evan.wj.service;

import com.evan.wj.dao.ArticleDao;
import com.evan.wj.pojo.Article;
import com.evan.wj.utils.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {
    @Autowired
    ArticleDao articleDao;
    public int saveArticle(Article article) {
        Object result = new Object();
        Article articleInfo =  articleDao.findbyArticleTitle(article.getTitle());
        if (articleInfo == null) {
            throw new MyException(412, "该文章没有写简介");
        }
        System.out.print(articleInfo.getTitle());
        String title = article.getTitle();
        String author = article.getAuthor();
        String date = article.getDate();
        String text = article.getText();
        String textleng = article.getTextleng();
        if (articleInfo != null) {
            return articleDao.saveActicle(title, date, author, text, textleng);
        } else {
            return 0;
        }
    }
}
