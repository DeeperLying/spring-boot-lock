package com.evan.wj.controller;

import com.evan.wj.pojo.ArticleList;
import com.evan.wj.result.Result;
import com.evan.wj.service.ArticleListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ArticleListController {

    @Autowired
    ArticleListService articleListService;

    @CrossOrigin
    @PostMapping(value = "api/saveArticleList")
    @ResponseBody
    public Result saveArticleList(@RequestBody ArticleList articleList) {
        String author = articleList.getAuthor();
        if (author != null) {
            int isSave = articleListService.save(articleList);
            if (isSave != 0) {
                return new Result(200);
            }
            return new Result(400);
        } else  {
            return new Result(400);
        }
    }

    @CrossOrigin
    @GetMapping(value = "api/getArticleList")
    @ResponseBody
    public Result getArticleList(@RequestParam("pageSize") int pageSize, @RequestParam("currentPage") int currentPage) {
        int startIndex = currentPage * pageSize;
        List<ArticleList> articleListList = articleListService.findByArticleList(startIndex, pageSize);
        return new Result(200, articleListList);
    }

}
