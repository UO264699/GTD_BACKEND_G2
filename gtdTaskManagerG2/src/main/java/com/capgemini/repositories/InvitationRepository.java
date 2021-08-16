package com.capgemini.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.model.Invitation;
import com.capgemini.model.User;

public interface InvitationRepository extends JpaRepository<Invitation, Long> {

	List<Invitation> findByUser(User user);
	
}
