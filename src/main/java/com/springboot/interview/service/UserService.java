package com.springboot.interview.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.springboot.interview.repository.UserRepository;
@Service
//@Scope("singleton")
@Scope("prototype")
public class UserService {
     // DI - require to user UserRepositoryImpl -->>
//	public static void main(String args[]) {
		//UserRepositoryImpl user = new UserRepositoryImpl();
	    // user.save();
//}
	
	// DI --> Hey, I need to inject UserRepository Impl...
	
	// step 1 : write the userRepository Interface and use the @Autowired Annotation --> field level injection
//	@Autowired
//	@Qualifier("userRepositoryImpl2")
	private UserRepository userRepository;
	
	
	// Step 2: Constructor injection 
	@Autowired
	
	public UserService(@Qualifier("userRepositoryImpl") UserRepository userRepository) {
		System.out.println("Testing beanscope " + ": singleton");
		this.userRepository = userRepository;
	}
    
	// Step 3: Setter Injection
//	@Autowired
//	@Qualifier("userRepositoryImpl")
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
