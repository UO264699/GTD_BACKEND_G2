package com.capgemini.services;

import java.util.List;
import java.util.Optional;

import com.capgemini.model.Category;
import com.capgemini.model.User;

public interface CategoryService {

	/**
	 * @return list of all categories
	 */
	List<Category> findAll();

	/**
	 * @param id
	 * @return category by id
	 */
	Category findById(Long id);
	
	/**
	 * @param user
	 * @return category searched by user id
	 */
	Category findByUserId(Long user);

	/**
	 * @param category
	 * @return category saved in DB
	 */
	Category save(Category category);

	/**
	 * @param id
	 * delete category by id
	 */
	void deleteById(Long id);

	/**
	 * @param category
	 * delete category by category
	 */
	void delete(Category category);

	/**
	 * delete all categories
	 */
	void deleteAll();
	
	/**
	 * @param user
	 * @return list of categories by user
	 */
	List<Category> findByUser(User user);

}
