package com.springboot.interview.repository;

import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository{

	@Override
	public void saveUser() {
		System.out.println("User is saved!");
	}
	
	UserRepositoryImpl(){
		System.out.println("This is getting initialized userRepository IMPl");
	}

}
