package com.capgemini.services;

import java.util.List;

import com.capgemini.model.Invitation;
import com.capgemini.model.User;

public interface InvitationService {

	/**
	 * Find all invitations
	 * 
	 * @return list of invitations
	 * @see org.springframework.data.jpa.repository.JpaRepository#findAll()
	 */
	List<Invitation> findAll();

	/**
	 * Find invitation by user
	 * 
	 * @param user
	 * @return list of invitations
	 * @see com.capgemini.repositories.InvitationRepository#findByUser(com.capgemini.model.User)
	 */
	List<Invitation> findByUser(User user);

	/**
	 * Delete invitation by id
	 * 
	 * @param id invitation's id
	 * @see org.springframework.data.repository.CrudRepository#deleteById(java.lang.Object)
	 */
	void deleteById(Long id);

	/**
	 * Save an invitation
	 * 
	 * 
	 * @param entity invitation
	 * @return invitation added
	 * @see org.springframework.data.repository.CrudRepository#save(java.lang.Object)
	 */
	<S extends Invitation> S save(S entity);

}