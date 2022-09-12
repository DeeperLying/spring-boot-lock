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
import java.util.List;
import java.util.Map;

@Repository
public interface ArticleListDao extends JpaRepository<ArticleList, Integer> {
    List<ArticleList> findAll();

    @Transactional
    @Modifying
    @Query(value = "insert into article_list(title,date,author,text) values (?1, ?2, ?3, ?4)", nativeQuery = true)
    int saveActicle(@Param("title") String title, @Param("date") String date, @Param("author") String author, @Param("text") String text);

    @Query(value = "select * from article_list where title=?1", nativeQuery = true)
    ArticleList findbyArticleTitle(@Param("title") String name);

    @Query(value = "SELECT * FROM article_list ORDER BY id LIMIT ?1, ?2", nativeQuery = true)
    List<ArticleList> findByArticleList(@Param("startIndex") int startIndex, @Param("overIndex") int overIndex);
}
