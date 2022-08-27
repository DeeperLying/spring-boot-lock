package com.evan.wj.controller;

import com.evan.wj.pojo.ArticleList;
import com.evan.wj.result.Result;
import com.evan.wj.service.ArticleListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ArticleListController {

    @Autowired
    ArticleListService articleListService;

    @CrossOrigin
    @PostMapping(value = "api/saveArticleList")
    @ResponseBody
    public Result saveArticleList(@RequestBody ArticleList articleList) {
        String author = articleList.getAuthor();
        System.out.print(author);
        if (author != null) {
            int isSave = articleListService.save(articleList);
            System.out.print('='+isSave);
            if (isSave != 0) {
                return new Result(200);
            }
            return new Result(400);
        } else  {
            return new Result(400);
        }
    }
}
