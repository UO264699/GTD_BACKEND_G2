package com.capgemini.services.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.model.User;
import com.capgemini.repositories.UserRepository;
import com.capgemini.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}
	@Override
	public Optional<User> findById(Long id) {
		return userRepository.findById(id);
	}

	@Override
	public User save(User users) {
		return userRepository.save(users);
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
