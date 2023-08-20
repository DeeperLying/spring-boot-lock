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
    String registrationToken = "fST4P1u3jCd52C5BweDg9V:APA91bFBaSVfWg-hpWw7U8A-f55xTlacunGuZZ6rxtTJly5W1SyXMQiyrLOsUi6F2Dy9aNuEvUhTJorZbc4WV2NWe4CRR-xClYETf2GduDmw-Q5S84lkOCMAe9FWmVlyhWyYfrPkhWCP";

    public void sendMessage() {
        // This registration token comes from the client FCM SDKs.

        // See documentation on defining a message payload.
        Message message = Message.builder()
                .setNotification(Notification.builder().setTitle("后端主动推送消息").setBody("快来看看啊后端发消息来啦").build())
                .setToken(registrationToken)
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
