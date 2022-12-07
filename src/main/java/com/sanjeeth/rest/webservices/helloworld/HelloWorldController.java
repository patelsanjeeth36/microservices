package com.sanjeeth.rest.webservices.helloworld;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.sanjeeth.rest.webservices.helloworld.HelloWorldBean;
//REST API
@RestController
public class HelloWorldController {
	// /hello-world
	 private MessageSource messageSource; 
	 
	 public HelloWorldController(MessageSource messageSource ) {
		 this.messageSource=messageSource;
	 }
//	@RequestMapping(method= RequestMethod.GET, path="/hello-world")
	@GetMapping(path="/hello-sanjeeth")
	public String helloWorld() {
		return "Hello Sanjeeth";
	}
	
	@GetMapping(path="/hello-sanjeeth-bean")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean ("HelloSanjeeth");
	}
	
	//Path Parameters
	// /users/{id}/todos/{id} =>/users/1/todos/101
	// /hello/path-variable/{name}
	
	@GetMapping(path="/hello-sanjeeth/path-variable/{name}")
	public HelloWorldBean helloWorldBeanPathVariable(@PathVariable String name) {
		//return new HelloWorldBean ("HelloSanjeethPathVariable " + name);
	//	or
		return new HelloWorldBean( String.format("Helloo sanjeeths, %s", name));
	}
	
	@GetMapping(path="/hello-sanjeeth-internationalized")
	public String helloWorldInternationalized() {
		Locale locale = LocaleContextHolder.getLocale();
		return messageSource.getMessage( "good.morning.message", null,  "Default Message", locale);
		// return "Hello Sanjeeth internationalized";
	}
	 
}
