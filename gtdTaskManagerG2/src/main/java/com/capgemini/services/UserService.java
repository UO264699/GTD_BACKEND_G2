package com.capgemini.services;

import java.util.List;

import com.capgemini.model.User;

public interface UserService {

	/**
	 * @return list of all users
	 */
	List<User> findAll();

	/**
	 * @param id
	 * @return user searched by id
	 */
	User findById(Long id);
	
	/**
	 * @param login
	 * @return user searched by login
	 */
	User findByLogin(String login);

	/**
	 * @param users
	 * @return save user in DB
	 */
	User save(User users);

	/**
	 * @param id
	 * delete user by id
	 */
	void deleteById(Long id);

	/**
	 * @param users
	 * delete user by user
	 */
	void delete(User users);

	/**
	 * delete all users
	 */
	void deleteAll();
}
