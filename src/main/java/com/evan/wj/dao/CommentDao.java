package com.evan.wj.dao;

import com.alibaba.fastjson.JSON;
import com.evan.wj.pojo.Comment;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.math.BigInteger;
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

//    @Transactional
//    public void save(Comment comment) {
//        String sql = "INSERT INTO `comments` (`user_id`,`article_id`,`nickname`,`avatar`,`content`,`Level`)" +
//                " VALUE (:user_id, :article_id, :nickname, :avatar, :content, :level)";
//        Query query =  entityManager.createNativeQuery(sql);
//        query.setParameter("user_id", comment.getUser_id());
//        query.setParameter("article_id", comment.getArticle_id());
//        query.setParameter("nickname", comment.getNickname());
//        query.setParameter("avatar", comment.getAvatar());
//        query.setParameter("content", comment.getContent());
//        query.setParameter("level", comment.getLevel());
//        int result = query.executeUpdate();
//        System.out.println(result);
//    }

    @Transactional
    public int save(Comment comment) {
        String sql = "INSERT INTO `comments` (`user_id`,`article_id`,`nickname`,`avatar`,`content`,`Level`, `parent_comment_id`)" +
                " VALUE (:user_id, :article_id, :nickname, :avatar, :content," +
                "CASE WHEN :parent_comment_id IS NOT NULL THEN 1 ELSE 0 END, " +
                "CASE WHEN :parent_comment_id <> 0 THEN :parent_comment_id ELSE NULL END)";
        Query query =  entityManager.createNativeQuery(sql);
        query.setParameter("user_id", comment.getUser_id());
        query.setParameter("article_id", comment.getArticle_id());
        query.setParameter("nickname", comment.getNickname());
        query.setParameter("avatar", comment.getAvatar());
        query.setParameter("content", comment.getContent());
        //query.setParameter("level", comment.getLevel());
        query.setParameter("parent_comment_id", comment.getParent_comment_id());
        int result = query.executeUpdate();
        System.out.println(result);
        return result;
    }

    public List getComments(int articleId) {
        String sql = "SELECT `nickname`, `avatar`, `content`, `like`, `update_time`, `parent_comment_id`, `id`, CASE WHEN" +
                " EXISTS(SELECT 1 FROM `comments` WHERE `parent_comment_id`=c.id) THEN true ELSE false END AS `has_children`" +
                "FROM `comments` AS c WHERE `article_id` = :articleId AND `parent_comment_id` is null ORDER BY `create_time` DESC";
        Query query =  entityManager.createNativeQuery(sql);
        query.setParameter("articleId", articleId);
        List<Object[]> rows = query.getResultList();
        List<Map<String, Object>> comments = new ArrayList<>();

        for (Object[] row: rows) {
            System.out.println(row[7] + "rows");
            System.out.println(row[7] != null);
            System.out.println(row[7].equals("1"));
            System.out.println(row[7].equals(1));
            System.out.println(row[7].getClass().getName());
            System.out.println(row[7].equals(new BigInteger("1")));
            Map<String, Object> comment = new HashMap<>();
            comment.put("nickname", row[0]);
            comment.put("avatar", row[1]);
            comment.put("content", row[2]);
            comment.put("like", row[3]);
            comment.put("update_time", row[4]);
            comment.put("parent_comment_id", row[5]);
            comment.put("id", row[6]);
            comment.put("has_children", (row[7] != null && row[7].equals(new BigInteger("1"))) ? true : false);
            comments.add(comment);
        }
        System.out.println(comments);

        return comments;
    }

    public List getCommentChildren(int commentId) {
        String sql = "SELECT `nickname`, `avatar`, `content`, `like`, `update_time`, `parent_comment_id`, `id` FROM `comments` WHERE `parent_comment_id`= :parent_comment_id";
        Query query =  entityManager.createNativeQuery(sql);
        query.setParameter("parent_comment_id", commentId);
        List<Object[]> rows = query.getResultList();
        List<Map<String, Object>> comments = new ArrayList<>();

        for(Object[] row: rows) {
            System.out.println(JSON.toJSON(row) + "ppp");
            Map<String, Object> comment = new HashMap<>();
            comment.put("nickname", row[0]);
            comment.put("avatar", row[1]);
            comment.put("content", row[2]);
            comment.put("like", row[3]);
            comment.put("update_time", row[4]);
            comment.put("parent_comment_id", row[5]);
            comment.put("id", row[6]);
            comments.add(comment);
        }

        return comments;
    }

    @Transactional
    public int updateLike(int commentId) {
        String sql = "UPDATE `comments` SET `like` = `like` + 1 WHERE `id` = :commentId";
        Query query =  entityManager.createNativeQuery(sql);
        query.setParameter("commentId", commentId);
        int result = query.executeUpdate();
        System.out.println(result);
        return result;
    }
}
