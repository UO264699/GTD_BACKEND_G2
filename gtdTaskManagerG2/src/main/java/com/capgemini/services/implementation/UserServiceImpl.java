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
	public Optional<User> findById(Long id) {
		return userRepository.findById(id);
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

	@Override
	public User findByLogin(String login) {
		return userRepository.findByLogin(login);
	}
}
