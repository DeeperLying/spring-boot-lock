package com.evan.wj.service;

import com.evan.wj.dao.UserDao;
import com.evan.wj.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author SuperLee
 * @date 2023/8/27 上午1:09
 */
@Service
public class FirbaseSerive {

    @Autowired
    UserDao userDao;

    public Result saveToken(int userId, String firebaseToken) {
        int isSave = userDao.updateUserFirebaseToken(userId, firebaseToken);
        if (isSave == 1) {
            return new Result(200, "success");
        }
        return new Result(400, "fail");
    }
}
