package com.evan.wj.dao;

import com.evan.wj.pojo.Article;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Repository
public interface ArticleDao extends JpaRepository<Article, Integer> {
    @Transactional
    @Modifying
    @Query(value = "insert into article(id,title,author,text,introduction,text_html,user_id, banner) values (null,?1,?2, ?3, ?4, ?5, ?6, ?7)", nativeQuery = true)
    int saveActicle(
        @Param("title") String title,
        @Param("author") String author,
        @Param("text") String text,
        @Param("introduction") String introduction,
        @Param("text_html") String text_html,
        @Param("user_id") int user_id,
        @Param("banner") String banner
    );

    // 写在这里没办法直接注入实体类ArticleList
    // @Query(value = "select * from articleList where title=?1", nativeQuery = true)
    // Map<String, String> findByArticleTitle(@Param("title") String name);

    @Query(value = "SELECT * FROM article WHERE id=?1", nativeQuery = true)
    Article findByArticle(@Param("id") int id);

    @Query(value = "SELECT id,author,introduction,title,create_time,text,text_html,user_id, banner FROM article ORDER BY id DESC LIMIT ?1, ?2", nativeQuery = true)
    List<Article> findByArticleList(@Param("startIndex") int startIndex, @Param("overIndex") int overIndex);

    @Query(value = "SELECT id,author,introduction,title,create_time,user_id, banner FROM article Where user_id = ?1 ORDER BY id DESC LIMIT ?2, ?3", nativeQuery = true)
    List<Map> findByUserArticleList(@Param("userId") int userId, @Param("startIndex") int startIndex, @Param("overIndex") int overIndex);
}
