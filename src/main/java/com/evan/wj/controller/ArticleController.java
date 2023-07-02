package com.evan.wj.controller;

import com.alibaba.fastjson.JSON;
import com.evan.wj.pojo.Article;
import com.evan.wj.pojo.ArticleList;
import com.evan.wj.result.Result;
import com.evan.wj.service.ArticleService;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class ArticleController {

    @Autowired
    ArticleService articleService;

    @CrossOrigin(allowCredentials = "true")
    @PostMapping(value = "api/saveArticle")
    @ResponseBody
    public Result saveArticle(@RequestBody Article article) {
        String author = article.getAuthor();
        System.out.println(JSON.toJSON(article));
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

    @CrossOrigin(allowCredentials = "true")
    @GetMapping(value = "api/getArticle")
    @ResponseBody
    public Result getArticle(@RequestParam("id") int id) {
        Article article = articleService.getArticle(id);
        return new Result(200, article);
    }

    @CrossOrigin(allowCredentials = "true")
    @GetMapping(value = "api/getArticleList")
    @ResponseBody
    public Result getArticleList(@RequestParam("pageSize") int pageSize, @RequestParam("currentPage") int currentPage) {
        int startIndex = currentPage * pageSize;
        List<Map> articleListList = articleService.findByArticleList(startIndex, pageSize);
        return new Result(200, articleListList);
    }

    @CrossOrigin(allowCredentials = "true")
    @GetMapping(value = "api/getUserArticleList")
    @ResponseBody
    public Result getUserArticleList(@RequestParam("pageSize") int pageSize, @RequestParam("currentPage") int currentPage, @RequestParam("userId") int userId) {
        int startIndex = currentPage * pageSize;
        List<Map> articleUserList = articleService.findByUserArticleList(startIndex, pageSize, userId);
        return new Result(200, articleUserList);
    }
}
