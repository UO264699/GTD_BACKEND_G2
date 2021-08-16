package com.capgemini.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

	/**
	 * @param login
	 * @return user searched by login
	 */
	Optional<User> findByLogin(String login);
}
