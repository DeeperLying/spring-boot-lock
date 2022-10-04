package com.evan.wj.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author SuperLee
 * @date 2022/9/25 下午1:46
 */

@Service
public class EmailService {

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    //application.properties中已配置的值
    @Value("${spring.mail.username}")
    private String sendEmailFrom;

    public boolean sendEmail(String email, HttpSession httpSession) {
        try {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject("Lee的博客，注册邮件");
        String code = randomCode();
        httpSession.setAttribute("email", email);
        httpSession.setAttribute("code", code);

        stringRedisTemplate.opsForValue().set(email, code, 2*60, TimeUnit.SECONDS);

        System.out.println(email);
        System.out.println(code);
        simpleMailMessage.setTo(email);
        simpleMailMessage.setFrom(sendEmailFrom);
        simpleMailMessage.setText("您的注册验证码是:" + code);
        javaMailSender.send(simpleMailMessage);
        return true;
        } catch (Exception exception) {
            System.out.println(exception);
            return false;
        }
    }

    public String randomCode(){
        StringBuilder str = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            str.append(random.nextInt(10));
        }
        return str.toString();
    }
}
