package com.evan.wj.controller;

import com.evan.wj.pojo.Comment;
import com.evan.wj.result.Result;
import com.evan.wj.service.CommentService;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author SuperLee
 * @date 2023/8/5 下午2:31
 */
@RestController
@RequestMapping("/api/comment")
@CrossOrigin(allowCredentials = "true")
public class CommentController {

    @Autowired
    CommentService commentService;

    @PostMapping(value = "/save")
    public void saveComment(@RequestBody Comment comment) {
        commentService.saveComment(comment);
    }

    @GetMapping(value = "/getComments")
    public Result getComments(@Param("articleId") int articleId) {
        System.out.println(articleId + "=====comment");
        List<Map> comments = commentService.getComments(articleId);
        return new Result(200, comments);
    }
}
