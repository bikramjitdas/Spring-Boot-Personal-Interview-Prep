package com.springboot.interview.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class CircularServiceDemo2 {
   @Autowired
   @Lazy
	private CircularServiceDemo1 cd1;
	public CircularServiceDemo2(CircularServiceDemo1 cd1) {
		this.cd1 = cd1;
	}
}
