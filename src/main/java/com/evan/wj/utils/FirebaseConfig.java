package com.evan.wj.utils;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;

/**
 * @author SuperLee
 * @date 2023/8/20 下午2:57
 */
@Configuration
public class FirebaseConfig {
    @Bean
    public FirebaseApp initializeFirebaseApp() throws Exception {
        FileInputStream serviceAccount = new FileInputStream("src/main/resources/xiaomaibu-c0234-firebase-adminsdk-o949h-4c46d061c9.json"); // 替换为您的私钥文件路径
        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("jdbc:mysql://127.0.0.1:3306/white_jotter") // 替换为您的项目数据库URL
                .build();

        return FirebaseApp.initializeApp(options);
    }
}
