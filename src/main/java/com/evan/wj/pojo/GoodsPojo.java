package com.evan.wj.pojo;

import lombok.Data;

import javax.persistence.*;

/**
 * @author SuperLee
 * @date 2023/5/7 下午2:15
 */

@Data
@Entity
@Table(name = "goodsList")
public class GoodsPojo {
    @Id
    @GeneratedValue
    @Column(name = "id")
    int id;

    String goods_title;

    String goods_image;

    int sex;

    String size;

    String goods_desc;

    int sale;
}
