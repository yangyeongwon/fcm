package com.fcm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fcm.service.FcmService;

@Controller
@RequestMapping(value="/fcm")
public class FcmController {
	
	@Autowired
	FcmService fcmservice;
	
}
