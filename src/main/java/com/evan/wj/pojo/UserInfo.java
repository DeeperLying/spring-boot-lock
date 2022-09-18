package com.evan.wj.pojo;

import lombok.Data;

import javax.persistence.*;

/**
 * @author SuperLee
 * @date 2022/9/12 上午11:44
 */

@Entity
@Table(name = "userInfo")
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    int id;

    @Column(name="name1", length = 10)
    String name1;

    @Column(name="age", columnDefinition = "VARCHAR(20) NOT NULL")
    int age;

    @Column(name="sex", length = 12)
    String sex;

}
