package com.springboot.interview.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.interview.repository.UserRepository;
@Service
public class UserService {
     // DI - require to user UserRepositoryImpl -->>
//	public static void main(String args[]) {
		//UserRepositoryImpl user = new UserRepositoryImpl();
	    // user.save();
//}
	
	// DI --> Hey, I need to inject UserRepository Impl...
	
	// step 1 : write the userRepository Interface and use the @Autowired Annotation --> field level injection
	//@Autowired
	private UserRepository userRepository;
	
	
	// Step 2: Constructor injection 
	//@Autowired
//	public UserService(UserRepository userRepository) {
//		this.userRepository = userRepository;
//	}
    
	// Step 3: Setter Injection
	@Autowired
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public void confirmUserSave() {
		userRepository.saveUser();
	}


	public UserRepository getUserRepository() {
		return userRepository;
	}


	
	
	
}
