package com.springboot.interview.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class CorsConfig implements WebMvcConfigurer{
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/api/**")
		.allowedOrigins("https://localhost:3000") // add your front-ends domain
		.allowedMethods("GET","POST","PUT","DELETE")
		.allowedHeaders("Origin","Content-Type","Accept","Authorization")
		.allowCredentials(true)
		.maxAge(3600);
	}
}
