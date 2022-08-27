package com.evan.wj.controller;

import com.evan.wj.pojo.Article;
import com.evan.wj.result.Result;
import com.evan.wj.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ArticleController {

    @Autowired
    ArticleService articleService;

    @CrossOrigin
    @PostMapping(value = "api/saveArticle")
    @ResponseBody
    public Result saveArticle(@RequestBody Article article) {
        String author = article.getAuthor();
        if (author != null) {
            int isSave = articleService.saveArticle(article);
            if (isSave != 0) {
                return new Result(200);
            }
            return new Result(400);
        } else  {
            return new Result(400);
        }
    }
}
