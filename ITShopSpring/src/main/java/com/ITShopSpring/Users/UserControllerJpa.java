package com.ITShopSpring.Users;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserControllerJpa {
	
	private UserDao service;
	
	private UserRepository userRepository;
	
	public UserControllerJpa(UserDao service, UserRepository userRepository) {
		this.service = service;
		this.userRepository = userRepository;
	}
	
	@GetMapping("/users")
	public List<User> retrieveAllUsers(){
		//return service.findAll();
		return userRepository.findAll();
	}
	
	@GetMapping("/users/{username}")
	public List<User> findbyUsername(@PathVariable String username){
		return userRepository.findByUsername(username);
	}
	
	
	/*
	 @GetMapping("/users/{id}")
	 
	public User retrieveUser(@PathVariable int id){
		return service.findOne(id);
	}
	*/
	
	/*
	@PostMapping("/users")
	public void createUser(@RequestBody User user) {
		service.save(user);
	}
	*/
}
