package com.sanjeeth.rest.webservices.user;

import java.net.URI;
import java.util.List;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;

@RestController
public class UserResource {
	
	private UserDaoService userDaoService;

	
	  public UserResource(UserDaoService userDaoService) {
		  super();
	  this.userDaoService = userDaoService; }
	 

	@GetMapping("/users")
	public List<User> retriveAllUser(){
		return  userDaoService.findAll();
		
	}
	
	  @GetMapping("/users/{id}") 
	  public User retriveOneUser(@PathVariable int id){
	  User user= userDaoService.findOne(id);
	  if(user==null)
		  throw new UserNotFoundException("id:"+id);
	  return user;
	  }
	  
	  @DeleteMapping("/users/{id}")
	  public  void deleteUser(@PathVariable int id) {
		 userDaoService.deleteById(id);
		   
	  }
	  
	  @PostMapping("/users")
	  public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		  User savedUser = userDaoService.save(user);
		// /users/4 => /users/{id},user.getId
	 URI location = ServletUriComponentsBuilder.fromCurrentRequest().
			 path( "/{id}")
			 .buildAndExpand(savedUser.getId())
			 .toUri();
		  
	  return ResponseEntity.created(location).build();
	  }
	  
	  @GetMapping("/usersHateoas/{id}")
	  public EntityModel<User> retriveUserHateoas(@PathVariable int id){
	  User user= userDaoService.findOne(id);
	  if(user==null)
		  throw new UserNotFoundException("id:"+id);
	  
	  EntityModel<User> entityModel = EntityModel.of( user);
	  
	  WebMvcLinkBuilder link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retriveAllUser());
			
	  entityModel.add( link.withRel( "all-users"));
	  
	  return entityModel;
	  }
	 
	  
}
