package com.evan.wj.pojo;

import lombok.Data;

import javax.persistence.*;

/**
 * @author SuperLee
 * @date 2022/9/12 上午11:44
 */

@Data
@Entity
@Table(name = "userInfo")
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name="name1", length = 110)
    String name1;

    @Column(name="age", length = 11)
    int age;

    @Column(name="sex", length = 12)
    String sex;

}
