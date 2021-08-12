package com.capgemini.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.model.Category;
import com.capgemini.model.User;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{

	/**
	 * @param user
	 * @return list of categories searched by user
	 */
	List<Category> findByUser(User user);

	/**
	 * @param user
	 * @return optional of category searched by user id
	 */
	Optional<Category> findByUserId(Long user);

}
