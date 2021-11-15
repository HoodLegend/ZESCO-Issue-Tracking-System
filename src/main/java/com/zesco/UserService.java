package com.zesco;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	private final UserRepository repo;
	
	@Autowired
	public UserService (UserRepository repo) {
		
		this.repo=repo;
		
	}
	
	@Transactional
	public Optional<User> findUserByEmail(String email){
		return repo.findByUserEmail(email);
	}
	
	public boolean userExists(String email) {
		
		return findUserByEmail(email).isPresent();
		
	}
	
	

}
