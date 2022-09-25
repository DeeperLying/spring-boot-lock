package com.evan.wj.service;

import com.evan.wj.dao.UserDao;
import com.evan.wj.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class UserService {
    @Autowired
    UserDao userDAO;

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
        String email = (String) httpSession.getAttribute("email");
        String code = (String) httpSession.getAttribute("code");
        System.out.println(email);
        System.out.println(code);
        System.out.println('-');

        String requestEmail = user.getEmail();
        String requestCode = user.getCode();
        System.out.println(requestEmail);
        System.out.println(requestCode);
        if (requestEmail.equals(email) && requestCode.equals(code)) {
            int isSavue =  userDAO.saveUser(user.getUsername(), user.getPassword(), user.getEmail());
            System.out.println(isSavue);
        } else {
            System.out.println("没进去");
        }
        return false;
    }
}
