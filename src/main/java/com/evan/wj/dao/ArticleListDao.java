package com.evan.wj.dao;

import com.evan.wj.pojo.ArticleList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleListDao extends JpaRepository<ArticleList, Integer> {
    List<ArticleList> findAll();
}
