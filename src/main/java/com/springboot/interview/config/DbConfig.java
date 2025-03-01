package com.springboot.interview.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix="spring.datasource")
public class DbConfig {
	
	private String url;
	private String username;
	private String password;
	@Override
	public String toString() {
		return "DbConfig [url=" + url + ", username=" + username + ", password=" + password + "]";
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public DbConfig() {
		
	}
	
	
	
}
