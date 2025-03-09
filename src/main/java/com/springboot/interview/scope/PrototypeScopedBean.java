package com.springboot.interview.scope;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class PrototypeScopedBean {
  
	PrototypeScopedBean(){
  System.out.println("ProtoTypeBean instantiated !!!");
	}
}
