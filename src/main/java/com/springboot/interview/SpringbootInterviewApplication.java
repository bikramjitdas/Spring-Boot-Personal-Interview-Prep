package com.springboot.interview;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import com.springboot.interview.config.DbConfig;
import com.springboot.interview.scope.SingletonScopedBean;
import com.springboot.interview.service.UserService;

import jakarta.annotation.PostConstruct;

@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class SpringbootInterviewApplication implements CommandLineRunner {
	
	@PostConstruct
	public void init() {
		System.out.println("Create postConstruct db connection !");
		// connection pool logic s
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
		

		// Class 3
		// How to avoid ambiguity in dependency injection 
		// Let's say you have two implementation classes of the same interface which one you are trying to inject.
		// Use @Qualifier--> to avoid ambiguity or  @Resource(name =  "")
		// Difference between @Qualifier and @Resource -->
		// @Qualifier ==> By spring default library , use by Type injection
		// @Resource ==> By Java default, use By Name injection
		
		
		// What are the Bean Scope and different types of bean scope ? 
		//  Bean scope determines the overall lifecycle and visibility of a bean in spring IOC container or how long an bean instance is alive or when it will be destroyed.
		
		// Singleton - Then Spring boot creates a single instance of the bean for the entire application context. By default it is present. Whenever going forward you want to create instance more it will get from the inmemory instead of a new one. 
		// Prototype - Spring boot creates multiple instances of the bean whenever it is requested. 
		// Request - used in Web Based applications. It creates a new instance of the bean for each HTTP request. After processing the request, the bean instance is typically discarded. Each time you hit /message, a new instance is getting created. {form data}
		// Session  - used in Web Based applications.Instances get created at each session only.That is until and unless the user is logged in, it will have the same instance, whenever the session expires, a new instance will get created once the user login in again.
		// Application - used in Web Based applications.The application scope is used in web based spring boot application to create a single instance of the bean for the entire web application context.
		// WebSocket - It creates a single instance of the bean per websocket session.
		
		UserService userserviceCheckScope = context.getBean(UserService.class);
		UserService userserviceCheckScope1 = context.getBean(UserService.class);
		UserService userserviceCheckScope2 = context.getBean(UserService.class);
		// Can we create custom bean scope ? What are some use cases?
		// Yes, implement Scope and usecases includes -> creation of bean instance for each thread if we want to do
	   // Implement left!
		
		// When to use Singleton And when to use Prototype scope.
		// Singleton Scope : 
		// 1. Database Configuration
		// 2. Service Layer
		// 3. Application Configuration
		
		// Prototype Scope:
		// 1. User sessions
		// 2. Thread Safety - each thread creates its own instance. Parser, builder
		// 3. Heavy initialization - Resource allocation with high initialization, avoid resource leak.
		
		// Can prototype be injected in Singleton bean and vice versa? In such case what would be the scope ?
		SingletonScopedBean sb1 = context.getBean(SingletonScopedBean.class);
		SingletonScopedBean sb2 = context.getBean(SingletonScopedBean.class);
		System.out.println(sb1.getProtobean().hashCode() + " : " + sb2.getProtobean().hashCode()); // same hashcode, prototype loses it's scope.
		
		// solution ?
		// 1. don't use autowire then,use application context. not recommended to use.
		// 2. use ObjectFactory<> , getObject 
		// 3. @Lookup , getInstance()  return null --> spring will create proxy instance for you

		
		// what is the difference between spring singleton and plain singleton?
		// spring singleton scope -> within the application context => If I run two different application context, it will give me two different instance
		// plain singleton scope -> within the jvm, single instance, if two different jvm then only two different instance.
		
		// Purpose of BeanPostProcessor in spring? How can use to customize bean initialization and destruction?
		
		// Before initialization only If I wish to validate the password, username, how to do --> kafka template validation, user request, property injection, custom initialization logic, aop, proxy creation, monitoring and logging.
		
		// Rest And HTTP requests -->
		// HTTP methods -->  RequestMapping -> Parent class of all the annotations.
		// 1. POST - create 
		// 2. PUT - Update
		// 3. GET - retrieve 
		// 4. PATCH - partial update
		// 5. DELETE - remove
		
		// Earlier, people used to follow - 
		// @RequestMapping(value = "/users",method = HttpMethod.POST)
		
		// Task Based scenario --> 
		// Design a rest end point, assume that you have a product database, and 
		// your task it to create an API to filter a list of products by productType.
		// Controller  -> Service -> Product
		// First thing, I have a product class as it is persistent in db.
		// Second write the service business logic in service layer
		// Third write the Controller -> @PathVariable
		
		//Design a rest end point, assume that you have a product database, and 
		// your task it to create an API to filter a list of products by productType, if the user provides this input, it should fetch on the basis of productType else if the endpoint is not provided, fetch all the products.
		
		// Use @RequestParams
		
		
		// Difference between @RequestParams and @PathVariable --> 
		// @PathVariable is mandatory to use. else throw 404 error
		// @RequestParams is optional
		
		// Difference between @RestController and @Controller 
		// @Controller - spring always finds the return as modelAndView, i.e, it will try to search with jsp, html pages in the resources folder --> We can change this by adding @ResponseBody and @GetMapping("/user",produces = MediaType.PLAIN_TEXT)
		// @RestController - ResponseBody + RequestMapping
		
		//How can we deserialize a JSON request payload into an object within a Spring MVC controller?
		// Use /PostMapping() @RequestBody Product product --> But the key should be same as the variable name being passed from the payload else BAD REQUEST
		
		//Can we perform update operation in POST http method if yes then why do we need PUT mapping or put http method?
		// yes, we can do, no issue but it violates the REST principle.
		// Idempotency nature of RestFul Webservices - on your subsequence action, if the resource is getting modified, therefore it is not idempotent. 
		// @PostMapping - Not IDEMPOTENT
		// @PutMapping - IDEMPOTENT - No new resource is created here.
		
		//  Can we pass Request Body in GET HTTP method?
		// Yes, but it's not  recommended to use.
		
		// How can we perform content negotiation (XML/JSON) in Rest endpoint
		//
	
		
		//What all status code you have observed in your application?
		// 4XX-
		// 400 - BAD Request
		// 404 - Not Found
		// 401 - Authentication Error
		// 403 - Not Authorized
		// 405 - Method Not Allowed
		// 415 - Unsupported mediatype
		// 5XX -
		// 500 - Internal Server
		// 502 - Bad Gateway - unhealthy application
		// 2XX -
		// 200 - OK, payload in response data
		// 201 - Created
		
		// How can you customize the status code for your endpoint?
		// use putMapping - > @ResponseStatus(HttpStatus.NO_CONTENT);
		
		// What is and  How can you enable cross origin in your project?
		
		//CORS (Cross-Origin Resource Sharing) is a security mechanism implemented by web browsers to control how web pages from one domain can request resources from another domain. It prevents malicious websites from making unauthorized requests to other domains, protecting user data and preventing cross-site request forgery (CSRF) attacks.

		// When a browser makes a cross-origin request (e.g., from https://example.com to https://api.example.com), it sends an OPTIONS pre-flight request to the server to check if the actual request is allowed. The server responds with headers indicating which origins, methods, and headers are permitted.


		
		// Basically, ideally there are two ways to enable CORS -
		// 1. We are having an annotation - @CrossOrigin(origins = "https://localhost:4000")
		// 2. Using CorsConfig handle using java-- implements WebMvcConfigurer and addCorsMappings(CorsRegistry registry) 
		
		// How can you upload a file in spring?
		// using class MultiPartFile --> Implementation I will add later using a small project...
		
		// How do you version your rest API's ?
		// /api/v1/addUser
		// /api/v2/addUser
		// pass in request param version --> pass the version in the api
		// pass in header param @RequestHeader(name="api-version") int version --> this is a header key and value to be put.
		
		// How can you document your rest API's ?
		// Spring Boot has support for the package Swagger/Open API 3, Can use it to document the API's.
		//http://localhost:8080/swagger-ui/index.html#/
		// if you want to share the json, do - http://localhost:8080/v3/api-docs
		// download - http://localhost:8080/v3/api-docs.yaml
		
		// How can you hide in the documents-
		// Use @Hidden for Swagger.
		
		
		
		
		
		
		
		
		
		
		
		
		
		
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
		System.out.println("Db config  " + dbconfig);
	}
	
}
