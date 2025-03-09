package com.springboot.interview.repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

//@Repository
@Component

public class UserRepositoryImpl2 implements UserRepository{

	@Override
	public void saveUser() {
		System.out.println("User is saved from UserRepositoryImpl2!");
	}
	UserRepositoryImpl2(){
		System.out.println("This is getting initialized userRepository IMPl2");
	}
}