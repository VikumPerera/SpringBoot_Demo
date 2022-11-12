package com.springboot.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	@Value("${my.data}")
	private String welcomeMessage;
	
	@GetMapping(value = "/")
	public String helloWorld() {
		return welcomeMessage;
	}
	
}
