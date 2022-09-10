package com.evan.wj.controller;

import com.alibaba.fastjson.JSON;
import com.evan.wj.pojo.Article;
import com.evan.wj.result.Result;
import com.evan.wj.service.ArticleService;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @CrossOrigin
    @GetMapping(value = "api/getArticle")
    @ResponseBody
    public Result getArticle(@RequestParam("id") int id) {
        Article article = articleService.getArticle(id);
        return new Result(200, article);
    }
}
