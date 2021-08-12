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
	 * @return
	 * @see org.springframework.data.jpa.repository.JpaRepository#findAll()
	 */
	@Override
	public List<Invitation> findAll() {
		return invitationRepository.findAll();
	}

	/**
	 * @param user
	 * @return
	 * @see com.capgemini.repositories.InvitationRepository#findByUser(com.capgemini.model.User)
	 */
	@Override
	public List<Invitation> findByUser(User user) {
		return invitationRepository.findByUser(user);
	}

	/**
	 * @param id
	 * @see org.springframework.data.repository.CrudRepository#deleteById(java.lang.Object)
	 */
	@Override
	public void deleteById(Long id) {
		invitationRepository.deleteById(id);
	}

	/**
	 * @param <S>
	 * @param entity
	 * @return
	 * @see org.springframework.data.repository.CrudRepository#save(java.lang.Object)
	 */
	@Override
	public <S extends Invitation> S save(S entity) {
		return invitationRepository.save(entity);
	}

	
	

}
