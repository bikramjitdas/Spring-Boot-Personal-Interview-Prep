package com.springboot.interview.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.interview.scope.RequestScopedBean;

@RestController
public class RequestScopeBeanController {
 
	@Autowired
	private RequestScopedBean requestbean;
	
	@GetMapping("/message")
	public String getMessage() {
		return requestbean.getMessage();
	}
}
