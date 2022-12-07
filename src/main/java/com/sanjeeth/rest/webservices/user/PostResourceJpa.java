package com.sanjeeth.rest.webservices.user;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sanjeeth.rest.webservices.jpa.PostRepository;
import com.sanjeeth.rest.webservices.jpa.UserRepository;

import jakarta.validation.Valid;

@RestController
public class PostResourceJpa {
	
	private UserRepository userRepository;
	private PostRepository postRepository;
	
	
	public PostResourceJpa(UserRepository userRepository, PostRepository postRepository) {
		super();
		this.userRepository = userRepository;
		this.postRepository = postRepository;
	}


	@GetMapping("jpa/users/{id}/posts")
	  public List<Post> getAllPostsForUsers(@PathVariable int id){
		 
		 Optional<User> usersfindById = userRepository.findById( id);
		 if(usersfindById.isEmpty())
			  throw new UserNotFoundException("id not found:"+id);
		 List<Post> postList = usersfindById.get().getPostList();
		 return postList;
	  }
	
	@PostMapping("jpa/users/{id}/posts")
	public  ResponseEntity<User> createPostUser(@PathVariable int id, @Valid  @RequestBody Post post) {
		Optional<User> findByIdUser = userRepository.findById( id);
		 
		 post.setUser(findByIdUser.get() );
		postRepository.save(post);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path( "/{id}").buildAndExpand( post.getId()).toUri();
 return 	ResponseEntity.created(uri).build();

	}
}
