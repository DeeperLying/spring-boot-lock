package com.evan.wj.pojo;

import lombok.Data;

import javax.persistence.*;

/**
 * @author SuperLee
 * @date 2023/5/20 下午12:28
 */

@Data
@Entity
@Table(name = "orderList")
public class OrderListPojo {
    @Id
    @GeneratedValue
    @Column(name = "id")
    int id;

    int goods_id;

    String out_trade_no;

    String goods_title;

    String goods_image;

    int sex;

    String size;

    String goods_desc;

    int sale;

    String order_status;
}
