package com.springboot.interview.scope;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
@Scope(value = WebApplicationContext.SCOPE_REQUEST,proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionScopedBean {
    private String messasge;
    
   SessionScopedBean(){
	   System.out.println("Session scoped bean here !");
	   this.messasge = "This is a session scoped bean, user is currently logged in";
   }
	public String getMessasge() {
		return messasge;
	}
	public void setMessasge(String messasge) {
		this.messasge = messasge;
	} 		
	   
	   
}
