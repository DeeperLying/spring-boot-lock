package com.evan.wj.dao;

import com.evan.wj.pojo.ArticleList;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface ArticleListDao extends JpaRepository<ArticleList, Integer> {
    List<ArticleList> findAll();

    @Transactional
    @Modifying
    @Query(value = "insert into articleList(title,date,author,text) values (?1, ?2, ?3, ?4)", nativeQuery = true)
    int saveActicle(@Param("title") String title, @Param("date") String date, @Param("author") String author, @Param("text") String text);
}
