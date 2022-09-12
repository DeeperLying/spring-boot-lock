package com.evan.wj.service;

import com.evan.wj.dao.ArticleListDao;
import com.evan.wj.pojo.ArticleList;
import org.joda.time.DateTime;
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

    public int save(ArticleList articleList) {
        String title = articleList.getTitle();
        String author = articleList.getAuthor();
        String date = articleList.getDate();
        String text = articleList.getText();
        return articleListdao.saveActicle(title, date, author, text);
    }

    public List<ArticleList> findByArticleList(int startIndex, int overIndex) {
        return articleListdao.findByArticleList(startIndex, overIndex);
    }
}
