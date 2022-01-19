package com.config;

import java.io.FileInputStream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;


@Configuration
public class FcmConfig {

	@Value("${fcm.path}")
	private Resource resource;
	 
	@SuppressWarnings("deprecation")
	@PostConstruct
	public void initFirebase() {
		try {
			FileInputStream serviceAccout = new FileInputStream(resource.getFile());
			FirebaseOptions options = new FirebaseOptions.Builder()
					.setCredentials(GoogleCredentials.fromStream(serviceAccout))
					.build();
			System.out.println("############## key path ::");
			System.out.println(resource);
			FirebaseApp.initializeApp(options);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	};
	
}
