package com.sanjeeth.rest.webservices.user;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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

import com.sanjeeth.rest.webservices.jpa.UserRepository;

import jakarta.validation.Valid;

@RestController
public class UserResourceJpa {
	
	 
	private UserRepository  userRepository;
	
	  public UserResourceJpa(   UserRepository userRepository) {
		  super();
	    
	  this.userRepository =userRepository; }

	@GetMapping("/jpa/users")
	public List<User> retriveAllUser(){
		return   userRepository.findAll();
		
	}
	
	  @GetMapping("/jpa/users/{id}") 
	  public Optional<User> retriveUser(@PathVariable int id){
	   Optional<User> userfindById = userRepository.findById(id);
	  if(userfindById.isEmpty())
		  throw new UserNotFoundException("id:"+id);
	  return userfindById;
	  }
	  
	  @DeleteMapping("/jpa/users/{id}")
	  public  void deleteUser(@PathVariable int id) {
		 userRepository.deleteById( id);
		   
	  }
	  
	  @PostMapping("/jpa/users")
	  public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		  User savedUser = userRepository.save(user);
		// /users/4 => /users/{id},user.getId
	 URI location = ServletUriComponentsBuilder.fromCurrentRequest().
			 path( "/{id}")
			 .buildAndExpand(savedUser.getId())
			 .toUri();
		  
	  return ResponseEntity.created(location).build();
	  }
	  
	  @GetMapping("/jpa/usersHateoas/{id}")
	  public EntityModel<User> retriveUserHateoas(@PathVariable int id){
		  Optional<User> userfindById = userRepository.findById(id);
	  if(userfindById.isEmpty())
		  throw new UserNotFoundException("id not found:"+id);
	  
	  EntityModel<User> entityModel = EntityModel.of( userfindById.get());
	  
	  WebMvcLinkBuilder link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retriveAllUser());
			
	  entityModel.add( link.withRel( "all-users"));
	  
	  return entityModel;
	  }
	  
	  
	  
	  
	 
	  
}
