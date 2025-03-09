package com.springboot.interview.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.interview.scope.SessionScopedBean;

@RestController
public class SessionScopeBeanController {
	
	@Autowired
	private SessionScopedBean sessionbean;
	
	@GetMapping("/sessionmessage")
	public String getMessage() {
		return sessionbean.getMessasge();
	}
}
