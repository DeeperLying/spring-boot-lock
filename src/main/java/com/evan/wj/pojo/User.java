package com.evan.wj.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class User {
    @Id
    @GeneratedValue
    @Column(name = "id")
    int id;

    String username;
    String password;
    String email;
    String phone;
    String headimgurl;

    @Transient
    String code;

}
