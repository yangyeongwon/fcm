package com.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/fcm")
public class MainController {
	
	@GetMapping(value = {"/", ""})
	public String index() {
		return "main/home";
	}
	
}
