package com.evan.wj.dao;

import com.evan.wj.pojo.User;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Map;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {
    User findByUsername(String userName);

    @Query(value = "SELECT id,username,email,phone,sex,role,headimgurl FROM user WHERE email=?1 and password=?2", nativeQuery = true)
    Map getByEmailAndPassword(String email, String password);

    @Query(value = "SELECT id,username,email,phone,sex,role,headimgurl FROM user WHERE phone=?1 and password=?2", nativeQuery = true)
    Map getByPhoneAndPassword(String phone, String password);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO user(id, username, password, email) VALUES(null, ?1, ?2, ?3)", nativeQuery = true)
    int saveUser(@Param("username") String username,@Param("password") String password, @Param("email") String email);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO user(id, username, password, phone) VALUES(null, ?1, ?2, ?3)", nativeQuery = true)
    int savePhoneUser(@Param("username") String username,@Param("password") String password, @Param("phone") String phone);

//    public void updateUserInfo() {
//        @PersistenceContext
//        private EntityManager entitymangager;
//    }
}
