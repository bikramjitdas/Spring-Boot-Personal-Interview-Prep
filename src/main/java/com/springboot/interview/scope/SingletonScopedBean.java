package com.springboot.interview.scope;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

@Component
public class SingletonScopedBean {

//	@Autowired
//	private PrototypeScopedBean protobean;
	
//	@Autowired
//	private ApplicationContext context;
	
	@Autowired
	private ObjectFactory<PrototypeScopedBean> protobeanFactory;
	
	public SingletonScopedBean() {
		System.out.println("Singleton Instantiated !!!!!");
	}
	
	public PrototypeScopedBean getProtobean() {
		//return context.getBean(PrototypeScopedBean.class);
		//return protobeanFactory.getObject();
		return getInstance();
	}
	@Lookup	
	public PrototypeScopedBean getInstance() {
		return null;
	}
	
}
