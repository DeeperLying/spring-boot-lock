package com.evan.wj.dao;

import com.evan.wj.pojo.WeChatUserInfoPojo;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * @author SuperLee
 * @date 2023/3/19 下午3:12
 */

@Repository
public interface WeChatUserInfoDao extends JpaRepository<WeChatUserInfoPojo, Integer > {

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO we_chat_user_info (openid, nickname, sex,city,province,country,headimgurl,privilege,unionid) VALUES(?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8, ?9)", nativeQuery = true)
    int saveWeChatUserInfo(@Param("openid") String openid,@Param("nickname") String nickname, @Param("sex") int sex
            ,@Param("city") String city,@Param("province") String province,@Param("country") String country,@Param("headimgurl") String headimgurl
            ,@Param("privilege") String privilege,@Param("unionid") String unionid);

}

