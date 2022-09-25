package com.evan.wj.dao;

import com.evan.wj.pojo.User;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {
    User findByUsername(String userName);

    User getByUsernameAndPassword(String userName, String password);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO user(id, username, password, email) VALUES(null, ?1, ?2, ?3)", nativeQuery = true)
    int saveUser(@Param("username") String username,@Param("password") String password, @Param("email") String email);
}
