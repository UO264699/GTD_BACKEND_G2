package com.capgemini.services;

import java.util.List;

import com.capgemini.model.Invitation;
import com.capgemini.model.User;

public interface InvitationService {

	/**
	 * @return
	 * @see org.springframework.data.jpa.repository.JpaRepository#findAll()
	 */
	List<Invitation> findAll();

	/**
	 * @param user
	 * @return
	 * @see com.capgemini.repositories.InvitationRepository#findByUser(com.capgemini.model.User)
	 */
	List<Invitation> findByUser(User user);

	/**
	 * @param id
	 * @see org.springframework.data.repository.CrudRepository#deleteById(java.lang.Object)
	 */
	void deleteById(Long id);

}