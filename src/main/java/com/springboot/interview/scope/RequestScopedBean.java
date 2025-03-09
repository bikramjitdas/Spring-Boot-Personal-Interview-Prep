package com.springboot.interview.scope;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
@Scope(value = WebApplicationContext.SCOPE_REQUEST,proxyMode = ScopedProxyMode.TARGET_CLASS)
public class RequestScopedBean {
	
	private String message;
	RequestScopedBean(){
		System.out.println("Request Scope Bean here! ");
		this.message = "This is a request scoped bean";
	}
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	// Check in controller as it is a web app

}
