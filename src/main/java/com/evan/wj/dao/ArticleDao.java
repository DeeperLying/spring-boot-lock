package com.evan.wj.dao;

import com.evan.wj.pojo.Article;
import com.evan.wj.pojo.ArticleList;
import io.lettuce.core.dynamic.annotation.Param;
import org.omg.CORBA.Any;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ArticleDao extends JpaRepository<Article, Integer> {
    @Transactional
    @Modifying
    @Query(value = "insert into article(id,title,date,author,text,textleng) values (?1,?2, ?3, ?4, ?5, ?6)", nativeQuery = true)
    int saveActicle(@Param("id") int id,@Param("title") String title,@Param("date") String date,@Param("author") String author,@Param("text") String text,@Param("textleng") String textlengt);

    // 写在这里没办法直接注入实体类ArticleList
    // @Query(value = "select * from articleList where title=?1", nativeQuery = true)
    // Map<String, String> findByArticleTitle(@Param("title") String name);

    @Query(value = "SELECT * FROM article WHERE id=?1", nativeQuery = true)
    Article findByArticle(@Param("id") int id);
}
