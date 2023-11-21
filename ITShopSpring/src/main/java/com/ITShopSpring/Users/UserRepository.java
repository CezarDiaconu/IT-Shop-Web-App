package com.ITShopSpring.Users;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	// public List<User> findByUsername(String username);
	public List<User> findAll();
    public User findByUsernameAndPassword(String username, String Password);
	public User deleteByUsername(String username);
	public User findByUsername(String username);
	
    
	@Override
    <S extends User> S save(S user);
}