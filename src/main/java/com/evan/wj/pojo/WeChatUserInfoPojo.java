package com.evan.wj.pojo;

import lombok.Data;
import org.apache.commons.lang.StringUtils;

import javax.persistence.*;
import java.lang.reflect.Array;
import java.util.List;

/**
 * @author SuperLee
 * @date 2023/3/11 下午4:19
 */
@Data
@Entity
@Table(name = "weChatUserInfo")
public class WeChatUserInfoPojo {

    @Id
    @GeneratedValue
    @Column(name = "id")
    int id;

    String openid;

    String nickname;

    int sex;

    String language;

    String city;

    String province;

    String country;

    String headimgurl;

    String privilege;

    String unionid;
}
