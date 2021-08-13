package com.capgemini.services.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.capgemini.model.User;
import com.capgemini.repositories.UserRepository;
import com.capgemini.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder; 
	
	
	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}
	@Override
	public User findById(Long id) {
		Optional<User> userOptional = userRepository.findById(id);
		if(userOptional.isEmpty()) {
			throw new IllegalArgumentException("No user found by this id: " + id);
		}
		return userOptional.get();
	}
	
	@Override
	public User findByLogin(String login) {
		Optional<User> userOptional = userRepository.findByLogin(login);
		if(userOptional.isEmpty()) {
			throw new IllegalArgumentException("No user found by this login: " + login);
		}
		return userOptional.get();
	}

	@Override
	public User save(User user) {
		
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		
		return userRepository.save(user);
	}

	@Override
	public void deleteById(Long id) {
		 userRepository.deleteById(id);
	}

	@Override
	public void delete(User users) {
		userRepository.delete(users);
	}

	@Override
	public void deleteAll() {
		userRepository.deleteAll();
	}
}
