package com.evan.wj.utils;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import org.springframework.stereotype.Component;

/**
 * @author SuperLee
 * @date 2023/8/20 下午3:23
 */
@Component
public class FirebaseEntity {

    public void sendMessage(String firebaseToken) {
        // This registration token comes from the client FCM SDKs.

        // See documentation on defining a message payload.
        Message message = Message.builder()
                .setNotification(Notification.builder().setTitle("快去看看吧").setBody("有人回复了您的评论").build())
                .setToken(firebaseToken)
                .build();

        // Send a message to the device corresponding to the provided
        // registration token.
        String response = null;
        try {
            response = FirebaseMessaging.getInstance().send(message);
        } catch (FirebaseMessagingException e) {
            e.printStackTrace();
            System.out.println(e + "error");
        }
// Response is a message ID string.
        System.out.println("Successfully sent message: " + response);
    }
}
