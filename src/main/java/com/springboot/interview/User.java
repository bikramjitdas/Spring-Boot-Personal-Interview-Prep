package com.springboot.interview;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class User {
	private int user_id;
	private String username;
	//@Autowired
	//private Role roleObj;
	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", username=" + username + "]";
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
//	public static void main(String args[]) {
////		Role roleObj = new Role();
////		roleObj.setRole("Developer");
////		roleObj.setRole_id(1);
////		System.out.println(roleObj);
//		roleObj.
//	}
}
