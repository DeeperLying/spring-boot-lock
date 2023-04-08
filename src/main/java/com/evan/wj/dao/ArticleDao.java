package com.evan.wj.dao;

import com.evan.wj.pojo.Article;
import com.evan.wj.pojo.ArticleList;
import io.lettuce.core.dynamic.annotation.Param;
import org.joda.time.DateTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public interface ArticleDao extends JpaRepository<Article, Integer> {
    @Transactional
    @Modifying
    @Query(value = "insert into article(id,title,date,author,text,introduction) values (null,?1,?2, ?3, ?4, ?5)", nativeQuery = true)
    int saveActicle(
        @Param("title") String title,
        @Param("date") Date date,
        @Param("author") String author,
        @Param("text") String text,
        @Param("introduction") String introduction
    );

    // 写在这里没办法直接注入实体类ArticleList
    // @Query(value = "select * from articleList where title=?1", nativeQuery = true)
    // Map<String, String> findByArticleTitle(@Param("title") String name);

    @Query(value = "SELECT * FROM article WHERE id=?1", nativeQuery = true)
    Article findByArticle(@Param("id") int id);

    @Query(value = "SELECT id,author,date,introduction,title,text FROM article ORDER BY id LIMIT ?1, ?2", nativeQuery = true)
    List<Article> findByArticleList(@Param("startIndex") int startIndex, @Param("overIndex") int overIndex);
}
