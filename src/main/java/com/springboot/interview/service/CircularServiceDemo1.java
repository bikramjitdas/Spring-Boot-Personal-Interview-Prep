package com.springboot.interview.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class CircularServiceDemo1 {
   
	private CircularServiceDemo2 cd2;
	
	@Autowired
	@Lazy
	public CircularServiceDemo1(CircularServiceDemo2 cd2) {
		this.cd2 = cd2;
	}
	
	
	
	
}
