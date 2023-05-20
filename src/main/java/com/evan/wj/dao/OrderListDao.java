package com.evan.wj.dao;

import com.evan.wj.pojo.OrderListPojo;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author SuperLee
 * @date 2023/5/20 下午12:27
 */
@Repository
public interface OrderListDao extends JpaRepository<OrderListPojo, Integer > {
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO order_list (id, goods_id, out_trade_no, goods_title, goods_image, sex, size, goods_desc, sale, order_status) VALUES(null,?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8, ?9)", nativeQuery = true)
    int saveGoodsOrder(@Param("goods_id") String goods_id, @Param("out_trade_no") String out_trade_no,
                  @Param("goods_title") String goods_title, @Param("goods_image") String goods_image,
                  @Param("sex") int sex, @Param("size") String size, @Param("goods_desc") String goods_desc,
                  @Param("sale") int sale, @Param("order_status") String order_status);


    @Transactional
    @Modifying
    @Query(value = "UPDATE order_list SET order_status=?2 WHERE out_trade_no=?1", nativeQuery = true)
    int updateGoodsOrderStatus(@Param("out_trade_no") String out_trade_no, @Param("trade_status") String trade_status);

    @Query(value = "SELECT * FROM order_list limit 10", nativeQuery = true)
    List<OrderListPojo> getOrderList();
}
