package com.ITShopSpring.Users;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import java.util.function.Predicate;

@Component
public class UserDao {

	private static int usersCount = 0;
	
	private static List<User> users = new ArrayList<>();
	static {
		users.add(new User(++usersCount,"alfac","cezar@gmail.com","untpepaine"));
		users.add(new User(++usersCount,"alfac","cezar@gmail.com","untpepaine"));
		users.add(new User(++usersCount,"alfac","cezar@gmail.com","untpepaine"));
	}
	public List<User> findAll(){
		return users;
	}
	
	public User save(User user) {
		user.setId(++usersCount);
		users.add(user);
		return user;
	}
	
	//predicate
	public User findOne(int id) {
		Predicate<? super User> predicate = user -> user.getId() == id;
		return users.stream().filter(predicate).findFirst().get();
	}
}
