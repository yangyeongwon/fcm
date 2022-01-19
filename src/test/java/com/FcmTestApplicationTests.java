package com;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.google.firebase.messaging.AndroidConfig;
import com.google.firebase.messaging.AndroidNotification;
import com.google.firebase.messaging.ApnsConfig;
import com.google.firebase.messaging.ApnsFcmOptions;
import com.google.firebase.messaging.BatchResponse;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.MulticastMessage;
import com.google.firebase.messaging.Notification;
import com.google.firebase.messaging.SendResponse;
import com.google.firebase.messaging.TopicManagementResponse;

@SpringBootTest
@TestPropertySource(properties = "spring.config.location=classpath:application-test.yml")
class FcmTestApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Test
	void intStreamTest() {
		
	}
	
	/**
	 * @description
	 *  특정기기데 대한 메세지 전송
	 */
	@Test
	void singleSend() {
		try {
			String token = "dJMCzWnjTZe-qsM9k_x0Tz:APA91bEAreDT2guB6wU1Q828ZYTECHLk_2SGGdGG7nWpV5xUoa2hav6jZSwOgAxuL7PdmPOfBo23ywmU4cATyoWN8WbXQLgd5UzNWeH-x8-0Yrg_Kb8ZQ56yH1DAG8IY3LE8PwX2kF8z";
//			String token = "123";	
			Notification notification = Notification.builder()
					.setTitle("타이틀")
					.setBody("내용")
					.build();

			
			Message message = Message.builder()
					.setNotification(notification)
					.setToken(token) 
					.build();
			 
			String response = FirebaseMessaging.getInstance().send(message);
			System.out.println("메세지 보내기 성공:" + response);
			
		} catch (FirebaseMessagingException e) {
			e.printStackTrace();
			System.out.println("에러발생");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @description
	 *  여러 기기에 대한 메세지 전송
	 */
	@Test
	void multicastSend() {
		try {

			List<String> tokens = Arrays.asList(
					"ezYhUdd9S2-0u8BCp0D9s-:APA91bEHC8O2Gjy1EGzJTCKP1ww20VExhjh_itE4MJWzJ4_28H9ceRuRhtUxt6SRpd_T_v4Y2rdVEmmQHsDcwa-ds3_uyUvYtWTdaZ827QJ3R_Ya69nw3olF2BiKqgW5i825Hgpyvhug",
					"123123"
			);

			Notification notification = Notification.builder()
					.setTitle("notification 타이틀!!!")
					.setBody("notification 내용")
					.setImage("JavaImage")
					.build();
			
			AndroidNotification androidNoti = AndroidNotification.builder()
					.setTitle("AOS 타이틀!!!")
					.setBody("AOS 내용")
					.setIcon("아이콘")
					
					.build();
			
			
			AndroidConfig android = AndroidConfig.builder()
					.setNotification(androidNoti)
					.putData("user", "user123")
					.build();
			
			MulticastMessage messages = MulticastMessage.builder()
					.setNotification(notification)
					.addAllTokens(tokens)
					.setAndroidConfig(android)
					.build();
			
			BatchResponse response2 = FirebaseMessaging.getInstance().sendMulticast(messages);
			System.out.println("푸시 에러 개수 :" + response2.getFailureCount());
			if(response2.getFailureCount() > 0) {
				List<SendResponse> responses = response2.getResponses();
				List<String> failedTokens = new ArrayList<>();
				for(int i=0; i<responses.size(); i++) {
					if(!responses.get(i).isSuccessful()) {
						//유효하지 않는 토큰
						System.out.println(tokens.get(i));
						//성공한경우: 메시지 ID 문자열을 반환
						System.out.println(responses.get(i).getMessageId());				
						//에러코드
						System.out.println(responses.get(i).getException().getErrorCode());	
						//예외가 발생한 HTTP 응답을 반환합니다
						System.out.println(responses.get(i).getException().getMessage());
					}
				}
			}

		} catch (FirebaseMessagingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @description
	 *  주제에 대한 메세지 전송
	 */
	@Test
	void topicSend() {
//		String topic = "testTopic";
		String topic = "nontopic";
		Notification notification = Notification.builder()
				.setTitle("주제(topic)전송")
				.setBody("주제 내용 테스트입니다.")
				.build();
		
		List<Message> messages = Arrays.asList(
			 Message.builder()
					.setNotification(notification)
					.setTopic(topic)
					.build()
		);
				
//		Message message = Message.builder()
//				.setNotification(notification)
//				.setTopic(topic)
//				.build();
		
		try {
			BatchResponse responses = FirebaseMessaging.getInstance().sendAll(messages);
			System.out.println("토픽 에러 :" + responses.getFailureCount());
			if(responses.getFailureCount() > 0) {
				List<SendResponse>listRes = responses.getResponses();
				for(int i=0; i<listRes.size(); i++) {
					if(!listRes.get(i).isSuccessful()) {
						//에러코드
						System.out.println(listRes.get(i).getException().getErrorCode());	
						//예외가 발생한 HTTP 응답을 반환합니다
						System.out.println(listRes.get(i).getException().getMessage());
					}
				}
			}
			
		} catch (FirebaseMessagingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * @description
	 *  주제 구독 및 취소
	 */
	@Test
	void topicSubscribe() {
		List<String> registrationTokens = Arrays.asList(
				"dHpE69HPSwin7nJ9XTqony:APA91bFnT733axMnlqPwQR4wfqjVQRmGyf-Bb5odrtPKHMXdHfbR8OoWh_TL4aPNgdnX-0SfXsjTONxKB6W-gG-XBwSTPRfiJwj-iRIlsLHkrSsBeH-Jxa0w3HsVRcDhgrn8g1saDVLV"
		);
		String topic = "testTopic";
		
		try {
		TopicManagementResponse response = FirebaseMessaging.getInstance().subscribeToTopic(
				registrationTokens, topic
		);
		System.out.println("주제 구독 성공 개수: " + response.getSuccessCount()); 	
		System.out.println("주제 구독 실패 개수 :" + response.getFailureCount());
		if(response.getFailureCount() > 0) {
			/* 
			 * 주제 관리 작업을 실행하는 동안 발생한 오류 목록을 가져옵니다.
			 * 
			 * return List<Error>
			 * Error
			 * 	- index: 오류와 관련된 등록 토큰의 인덱스입니다.
			 *  - reason: 오류의 특성을 설명하는 문자열입니다.
			*/
			System.out.println(response.getErrors());
		}
		
		} catch(FirebaseMessagingException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
 	}
}
