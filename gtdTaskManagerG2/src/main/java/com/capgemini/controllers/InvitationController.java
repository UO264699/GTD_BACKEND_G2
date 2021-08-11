package com.capgemini.controllers;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.model.Invitation;
import com.capgemini.model.User;
import com.capgemini.services.implementation.InvitationServiceImpl;
import com.capgemini.services.implementation.UserServiceImpl;

@RestController
@RequestMapping("/invitations")
public class InvitationController {

	@Autowired
	private InvitationServiceImpl invitationServiceImpl;
	
	@Autowired
	private UserServiceImpl userServiceImpl;

	/**
	 * @return
	 * @see com.capgemini.services.implementation.InvitationServiceImpl#findAll()
	 */
	@GetMapping("/")
	public List<Invitation> findAll() {
		return invitationServiceImpl.findAll();
	}
	
	/**
	 * @return
	 * @see com.capgemini.services.implementation.InvitationServiceImpl#findAll()
	 */
	@GetMapping("/{id}")
	public List<Invitation> findByUserId(@PathVariable Long id) {
		
		User user = userServiceImpl.findById(id).get();
		
		return invitationServiceImpl.findByUser(user);
	}
	

}
