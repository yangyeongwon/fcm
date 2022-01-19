package com.fcm.service;

import org.springframework.stereotype.Service;

import com.google.firebase.FirebaseApp;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FcmServiceImpl implements FcmService{

	@Override
	public void sendToToken() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendToTopic() {
		try {
			String token = "ekn6hJzhSfSQemOoQ-6Txx:APA91bEhhhcuQS0Eo8jwnVv2UP4M6nZjpkiSO6yQ9OlHm1FG8cVFP0_70eXIivbXPGKL1wTatoCuASmhyqZHSLwWaJNmVY_RGoh8pKscGtJTYRpj_uuMJSl6QwKGa7uf1ia2YdUTT17y";
			Message message = Message.builder()
					.putData("score", "85")
					.putData("time", "2:45")
					.setToken(token)
					.build();
			
			String response = FirebaseMessaging.getInstance(FirebaseApp.getInstance("t-app-push")).send(message);
			
			System.out.println("메세지 보내기 성공:" + response);
		} catch(FirebaseMessagingException e){
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}
