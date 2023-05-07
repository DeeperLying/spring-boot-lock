package com.evan.wj.dao;

import com.evan.wj.pojo.GoodsPojo;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author SuperLee
 * @date 2023/5/7 下午2:33
 */

@Repository
public interface GoodsDao extends JpaRepository<GoodsPojo, Integer > {
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO goods_list (id,goods_title, goods_image, sex,size,goods_desc, sale) VALUES(null,?1, ?2, ?3, ?4, ?5, ?6)", nativeQuery = true)
    int saveGoods(@Param("goods_title") String goods_title, @Param("goods_image") String goods_image, @Param("sex") int sex
            , @Param("size") String size, @Param("goods_desc") String goods_desc, @Param("sale") int sale);

    @Query(value = "SELECT * from goods_list LIMIT 10", nativeQuery = true)
    List<GoodsPojo> getGoodsList();
}
