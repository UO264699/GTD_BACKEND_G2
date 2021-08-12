package com.capgemini.services.implementation;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.model.Invitation;
import com.capgemini.model.User;
import com.capgemini.repositories.InvitationRepository;
import com.capgemini.services.InvitationService;


@Service
public class InvitationServiceImpl implements InvitationService   {

	@Autowired
	private InvitationRepository invitationRepository;

	/**
	 * Find all invitations
	 * 
	 * @return list of invitations
	 * @see org.springframework.data.jpa.repository.JpaRepository#findAll()
	 */
	@Override
	public List<Invitation> findAll() {
		return invitationRepository.findAll();
	}

	/**
	 * Find invitation by user
	 * 
	 * @param user
	 * @return list of invitations
	 * @see com.capgemini.repositories.InvitationRepository#findByUser(com.capgemini.model.User)
	 */
	@Override
	public List<Invitation> findByUser(User user) {
		return invitationRepository.findByUser(user);
	}

	/**
	 * Delete invitation by id
	 * 
	 * @param id invitation's id
	 * @see org.springframework.data.repository.CrudRepository#deleteById(java.lang.Object)
	 */
	@Override
	public void deleteById(Long id) {
		invitationRepository.deleteById(id);
	}

	/**
	 * Save an invitation
	 * 
	 * 
	 * @param entity invitation
	 * @return invitation added
	 * @see org.springframework.data.repository.CrudRepository#save(java.lang.Object)
	 */
	@Override
	public <S extends Invitation> S save(S entity) {
		return invitationRepository.save(entity);
	}

	
	

}
