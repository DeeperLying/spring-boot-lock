package com.evan.wj.dao;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

/**
 * @author SuperLee
 * @date 2023/7/23 下午1:58
 */

@Repository
public class UserCustomizeDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void updateUserInfo(int userId, String headimgurl) {
        String sql = "UPDATE `user` SET `headimgurl` = CASE WHEN :headimgurl IS NOT NULL THEN :headimgurl ELSE `headimgurl` END WHERE `id` = :userId";
        Query query =  entityManager.createNativeQuery(sql);
        query.setParameter("userId", userId);
        query.setParameter("headimgurl", headimgurl);
        //query.setParameter("updateHeadImgUrl", 0);
        int result = query.executeUpdate();
        System.out.println(result);
    }
}
