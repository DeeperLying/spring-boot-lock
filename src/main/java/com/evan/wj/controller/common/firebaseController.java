package com.evan.wj.controller.common;

import com.evan.wj.utils.FirebaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.annotations.Mapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author SuperLee
 * @date 2023/8/20 下午3:19
 */
@RestController
@RequestMapping(value = "/api/firebase")
@CrossOrigin(allowCredentials = "true")
public class firebaseController {
    @Autowired
    FirebaseEntity firebaseEntity;

    @GetMapping(value = "/sendMessage")
    public void sendMessage() {
        firebaseEntity.sendMessage();
    }
}
