package com.ITShopSpring.Users;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ITShopSpring.Devices.Device;

@RestController
public class UserControllerJpa {
	
	
	private UserRepository userRepository;
	
	public UserControllerJpa(UserRepository userRepository) {
		
		this.userRepository = userRepository;
	}
	
	/*
	@GetMapping("/users")
	public List<User> retrieveAllUsers(){
		//return service.findAll();
		return userRepository.findAll();
	}
	
	
	@GetMapping("/users/{username}")
	public List<User> findbyUsername(@PathVariable String username){
		return userRepository.findByUsername(username);
	}
	*/
	
	 @PostMapping("/checkUser")
	 public ResponseEntity<String> checkUser(@RequestBody Map<String, String> userData) {
	        String username = userData.get("username");
	        String password = userData.get("password");

	        System.out.println("Received username: " + username);
	        System.out.println("Received password: " + password);

	        // Check if a user with the same username and password already exists
	        User existingUser = userRepository.findByUsernameAndPassword(username, password);

	        if (existingUser != null) {
	            // If user exists, send a success response to the React app
	            return ResponseEntity.ok("User exists!");
	        } else {
	            // If user doesn't exist, send a not found response to the React app
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found!");
	        }
	    }
	 
		 @PostMapping("/createUser")
		 public ResponseEntity<String> createUser(@RequestBody User user) {
			 
			 	String username = user.getUsername();
			    String email = user.getEmail();
			    String password = user.getPassword();
		        
		        System.out.println("Received username: " + username);
		        System.out.println("Received email: " + email);
		        System.out.println("Received password: " + password);
			    
		        User existingUser = userRepository.findByUsernameAndPassword(username, password);
	
		        
		        if (existingUser != null) {
		            // If user already exists, respond with a message indicating that
		            return ResponseEntity.ok("User already exists!");
		        } else {
		            // If user doesn't exist, save the new user
		            userRepository.save(user);
	
		            // Respond with a success message
		            return ResponseEntity.ok("User created successfully!");
		        } 
		 }
		
		  @PatchMapping("/update-user")
		  public ResponseEntity<User> updateUser(@RequestBody Map<String, String> updateData){
			  String userUsername = updateData.get("username");
			  String userPassword = updateData.get("password");
			  String whatToUpdate = updateData.get("whatToUpdate");
			  String infoToUpdate = updateData.get("infoToUpdate");
			  
			  
			  User user = userRepository.findByUsernameAndPassword(userUsername, userPassword);
			  
			  if( user != null) {
				  switch (whatToUpdate) {
				  	case "username":
				  		user.setUsername(infoToUpdate);
				  		break;
				  	case "email":
				  		user.setEmail(infoToUpdate);
				  		break;
				  	case "password":
				  		user.setPassword(infoToUpdate);
				  		break;
				  	default: break;
				  }
				userRepository.save(user);
				return ResponseEntity.ok(user);
			  }
			  else {
		    		return ResponseEntity.notFound().build();
		    	}
		  }
		  
		  @DeleteMapping("/delete-user")
		  public ResponseEntity<String> deleteUser(@RequestBody Map<String, String> deleteUser){
			  String username = deleteUser.get("username");
			  
			 User user = userRepository.findByUsername(username);
			  
			  if( user != null)
			  {
				  userRepository.deleteByUsername(username);
				  return ResponseEntity.ok("User deleted");
			  }
			  else {
				  return ResponseEntity.notFound().build();
			  }
		  }
	}
