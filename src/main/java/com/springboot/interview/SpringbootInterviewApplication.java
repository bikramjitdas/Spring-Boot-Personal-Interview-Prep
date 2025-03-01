package com.springboot.interview;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

import com.springboot.interview.config.DbConfig;
import com.springboot.interview.service.UserService;

import jakarta.annotation.PostConstruct;

@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class SpringbootInterviewApplication implements CommandLineRunner {
	
	@PostConstruct
	public void init() {
		System.out.println("Create postConstruct db connection !");
		// connection pool logic 
		// kafka producer consumer logic
		// data setting
	}

	public static void main(String[] args) {
		System.out.println("Hey, welcome to Spring boot launch! We are going to create the app context, register the beans and run the embedded server! ");
		ApplicationContext context = SpringApplication.run(SpringbootInterviewApplication.class, args);
		System.out.println("Wow, you have created the appcontext, registered the beans and run the embedded server from main  !");	
		
		// 1. Tight coupling --> 
		//Role roleObj = new Role();
		//roleObj.setRole("Developer");
		//roleObj.setRole_id(1);
		
		// Dependency Injection --> My dependency class is the parent and dependency class is the user and dependent class is the role.
		// Step 1: Create UserInterface --> I can turn to partial loose coupling By Creating an interface and calling the new ======>
		UserInterface roleObjInterface = new Role();
		roleObjInterface.setRole("Interface Developer");
		roleObjInterface.setRole_id(3);
		System.out.println(roleObjInterface.toString());
		
		// Step 2: Factory class also does partial dependenchy
		UserInterface roleObjfactory= UserFactory.getInstance();
		roleObjfactory.setRole("Factory Developer");
		roleObjfactory.setRole_id(4);
		System.out.println(roleObjfactory.toString());
		
		
		// Step 3. Dependency Injection - 1 - Field Level
//		UserService userservice = context.getBean(UserService.class);
//		userservice.confirmUserSave();
		
//		// Step 4: Dependency Inject - 2 Constructor Injection 
//		UserService userservice = context.getBean(UserService.class);
//		userservice.confirmUserSave();
		
//		// Step 5: Dependency Inject - 3 Setter Injection 
		UserService userservice = context.getBean(UserService.class);
		userservice.confirmUserSave();
		
		// Follow up questions -->
		//How Field Injection Works Without a Setter / Difference Between setter and field injection
		/*  Reflection:

			Spring uses Java’s reflection API to access and modify the private field directly.
			This bypasses the need for a setter method.
			Dependency Resolution:
			Since reflection allows direct access to private fields, a setter method is not necessary.
			Why Field Injection is Not Setter Injection
			Setter Injection explicitly uses a setter method to inject the dependency.
			Field Injection directly injects the dependency into the field using reflection.*/
		
		// When to use which injection ? 
		/* fieldlevel --> avoid due to testing limitations and visibility 
		 * constructor --> always use, use when the dependencies requires to be injected mandatory. Immutable nature. Also, circular dependency cannot be removed.
		 * setter --> when you require optional dependencies, or can dependencies can change after object creation, allow flexibilities and visibility of dependencies*/
		
		// what are circular dependencies -->
		/*CircularServiceDemo1 injects CircularServiceDemo2
		 * CircularServiceDemo2 injects CircularServiceDemo1*/
		
		// How can we avoid circular dependency? 
		// By using setter injection and saying that only Lazy initialization will take place, that is object initialization will only happen when it will be required.
		
		/// What is command Line Runner, when it will be invoked ? 
		/*  It is an interface provided by the Spring boot, to perform preprocessing logic/ data setting logic after bean creation in IOC Container */
		// Which one will get invoked in which order --> 1. Spring run main, 2.PostConstruct, 3.CLI run
		
		// what is the use of @value annotation --> Get the value from runtime from the properties/yml file
		// what is the alternate of @value --> use the object of environment, add from environment var; both val and env can be used.
		
		// which file gets loaded first, application yml or properties --> propertySourceLoader, YamlSourceLoader, propertySourceLoad first. 
		
		// How to load external properties in Spring Boot?
		// spring.config.import=c://documents/test.txt in application properties/application yaml
		
		//How map or bind/ load config properties to java object?
		// Using @ConfigurationProperties(prefix="spring.datasource") --> check dbconfig class
		
	}
	@Value("${global.default.currency}")
	private String defaultCurrency;
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private DbConfig dbconfig;
	@Override
	public void run(String... args) throws Exception {
		
		System.out.println("Hey welcome to cli execution!");
		System.out.println("Global default currency is " + defaultCurrency);
		System.out.println("Global default currency using environment object" + environment.getProperty("global.default.currency"));
		System.out.println(" Db config  " + dbconfig);
	}
	
	

}
