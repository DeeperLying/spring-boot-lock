package com.evan.wj.dao;

import com.evan.wj.pojo.Comment;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author SuperLee
 * @date 2023/8/5 下午4:40
 */
@Repository
public class CommentDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void save(Comment comment) {
        String sql = "INSERT INTO `comments` (`user_id`,`article_id`,`nickname`,`avatar`,`content`,`Level`)" +
                " VALUE (:user_id, :article_id, :nickname, :avatar, :content, :level)";
        Query query =  entityManager.createNativeQuery(sql);
        query.setParameter("user_id", comment.getUser_id());
        query.setParameter("article_id", comment.getArticle_id());
        query.setParameter("nickname", comment.getNickname());
        query.setParameter("avatar", comment.getAvatar());
        query.setParameter("content", comment.getContent());
        query.setParameter("level", comment.getLevel());
        int result = query.executeUpdate();
        System.out.println(result);
    }

    public List getComments(int articleId) {
        String sql = "SELECT `nickname`, `avatar`, `content`, `like`, `update_time`, `parent_comment_id` FROM `comments` WHERE `article_id` = :articleId";
        Query query =  entityManager.createNativeQuery(sql);
        query.setParameter("articleId", articleId);
        List<Object[]> rows = query.getResultList();
        List<Map<String, Object>> comments = new ArrayList<>();

        for (Object[] row: rows) {
            Map<String, Object> comment = new HashMap<>();
            comment.put("nickname", row[0]);
            comment.put("avatar", row[1]);
            comment.put("content", row[2]);
            comment.put("like", row[3]);
            comment.put("update_time", row[4]);
            comment.put("parent_comment_id", row[5]);
            comments.add(comment);
        }
        System.out.println(comments);

        return comments;
    }
}
