package com.evan.wj.service;

import com.alibaba.fastjson.JSON;
import com.evan.wj.dao.UserDao;
import com.evan.wj.pojo.User;
import com.evan.wj.utils.FirebaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    UserDao userDAO;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    FirebaseEntity firebaseEntity;

    public boolean isExist(String username) {
        User user = getByName(username);
        return null!=user;
    }

    public User getByName(String username) {
        return userDAO.findByUsername(username);
    }

    public Map get(String email, String password){
        return userDAO.getByEmailAndPassword(email, password);
    }

    public Map getPhone(String phone, String password){
        return userDAO.getByPhoneAndPassword(phone, password);
    }

    public void add(User user) {
        userDAO.save(user);
    }

    public boolean register(User user, HttpSession httpSession) {
        // 这种写法是什么意思
        // String email = (String) httpSession.getAttribute("email");
        // String code = (String) httpSession.getAttribute("code");
        String email = user.getEmail();
        String code = user.getCode();
        boolean isCachingCode = stringRedisTemplate.hasKey(email);
        if (isCachingCode) {
            String cachingCode = stringRedisTemplate.opsForValue().get(email);
            if (cachingCode.equals(code)) {
                int isSave =  userDAO.saveUser(user.getUsername(), user.getPassword(), user.getEmail());
                if (isSave == 1) {
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    public void getFirebaseToken(int userId) {
       Map userMap =  userDAO.findUserFirebaseToken(userId);
       System.out.println(JSON.toJSON(userMap) + ":ppppp");
       String firebaseToken = (String) userMap.get("firebase_token");
       if (!firebaseToken.isEmpty()) {
            System.out.println("发送推送");
           firebaseEntity.sendMessage(firebaseToken);
       }
    }
}
