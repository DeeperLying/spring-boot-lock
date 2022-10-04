package com.evan.wj.service;

import com.evan.wj.dao.UserDao;
import com.evan.wj.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class UserService {
    @Autowired
    UserDao userDAO;
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    public boolean isExist(String username) {
        User user = getByName(username);
        return null!=user;
    }

    public User getByName(String username) {
        return userDAO.findByUsername(username);
    }

    public User get(String username, String password){
        return userDAO.getByUsernameAndPassword(username, password);
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
}
