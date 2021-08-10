package com.capgemini.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
