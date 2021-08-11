package com.capgemini.services.implementation;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.model.Invitation;
import com.capgemini.model.User;
import com.capgemini.repositories.InvitationRepository;

@Service
public class InvitationServiceImpl {

	@Autowired
	private InvitationRepository invitationRepository;

	/**
	 * @return
	 * @see org.springframework.data.jpa.repository.JpaRepository#findAll()
	 */
	public List<Invitation> findAll() {
		return invitationRepository.findAll();
	}

	/**
	 * @param user
	 * @return
	 * @see com.capgemini.repositories.InvitationRepository#findByUser(com.capgemini.model.User)
	 */
	public List<Invitation> findByUser(User user) {
		return invitationRepository.findByUser(user);
	}



	

}
