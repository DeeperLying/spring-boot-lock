package com.evan.wj.service;

import com.evan.wj.dao.CommentDao;
import com.evan.wj.pojo.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author SuperLee
 * @date 2023/8/5 下午4:08
 */
@Service
public class CommentService {
    @Autowired
    CommentDao commentDao;

    public int saveComment(Comment comment) {
        int result = commentDao.save(comment);
        return result;
    }

    public List<Map> getComments(int articleId) {
       List<Map> comments = commentDao.getComments(articleId);
       return comments;
    }

    public List getCommentChildren(int commentId) {
        List comments = commentDao.getCommentChildren(commentId);
        return comments;
    }

    public int updateCommentLike(int commentId) {
        int isSave = commentDao.updateLike(commentId);
        return isSave;
    }
}
