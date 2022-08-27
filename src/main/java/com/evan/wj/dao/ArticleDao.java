package com.evan.wj.dao;

import com.evan.wj.pojo.Article;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface ArticleDao extends JpaRepository<Article, Integer> {
    @Transactional
    @Modifying
    @Query(value = "insert into article(id,title,date,author,text,textleng) values (6,?1, ?2, ?3, ?4, ?5)", nativeQuery = true)
    int saveActicle(@Param("title") String title,@Param("date") String date,@Param("author") String author,@Param("text") String text,@Param("textleng") String textlengt);
}
