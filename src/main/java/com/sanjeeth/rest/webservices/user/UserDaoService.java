package com.sanjeeth.rest.webservices.user;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

@Component
public class UserDaoService {

	// public List<User user) findAll
	// public User save(User user)
	// public User findOne(int id)
	
private static List<User> users =new ArrayList<>();
private static int userCount=0;	 
static {
	users.add( new User(++userCount,"adam",LocalDate.now().minusYears(30)));
	users.add( new User(++userCount,"Eve",LocalDate.now().minusYears(25)));
	users.add( new User(++userCount,"Jim",LocalDate.now().minusYears(20)));
}

public List<User> findAll(){
	return users;
}

  public User findOne(int id){
Predicate<? super User> predicate = user -> user.getId().equals(id);
  // return users.stream().filter(predicate).findFirst().get();
return users.stream().filter(predicate).findFirst().orElse(null);
  }
  
  
  public void deleteById(int id) {
	  // to remove matching object
	   Predicate<? super User> predicate = user -> user.getId().equals(id);
	   users.removeIf(predicate);
  }
  
  public User save(User user) {
	  user.setId(++userCount);
	  users.add(user);
	  return user;
  }
  
  
 
}
