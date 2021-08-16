package com.capgemini.controllers;

import java.util.ArrayList;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.model.Invitation;
import com.capgemini.model.TaskGroup;
import com.capgemini.model.User;
import com.capgemini.services.InvitationService;
import com.capgemini.services.TaskGroupService;
import com.capgemini.services.UserService;
import com.capgemini.services.implementation.InvitationServiceImpl;
import com.capgemini.services.implementation.TaskGroupServiceImpl;
import com.capgemini.services.implementation.UserServiceImpl;

@RestController
@RequestMapping("/invitations")
public class InvitationController {

	@Autowired
	private InvitationService invitationServiceImpl;
	
	@Autowired
	private UserService userServiceImpl;
	
	@Autowired
	private TaskGroupService taskGroupService;

	/**
	 * Find all invitations
	 * 
	 * @return list of invitations
	 * @see com.capgemini.services.implementation.InvitationServiceImpl#findAll()
	 */
	@GetMapping("/")
	public List<Invitation> findAll() {
		return invitationServiceImpl.findAll();
	}
	
	/**
	 * Find invitations by user id
	 * 
	 * @return list of invitations
	 * @see com.capgemini.services.implementation.InvitationServiceImpl#findByUserId()
	 */
	@GetMapping("/{id}")
	public List<Invitation> findByUserId(@PathVariable Long id) {
		
	
			
			User user = userServiceImpl.findById(id);
			return invitationServiceImpl.findByUser(user);
		
		
	}

	/**
	 * Delete invitation by id
	 * 
	 * @param id invitation's id
	 * @see com.capgemini.services.implementation.InvitationServiceImpl#deleteById(java.lang.Long)
	 */
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable Long id) {
		
		invitationServiceImpl.deleteById(id);
	}

	/**
	 * Save an invitation
	 * 
	 * @param entity invitation 
	 * @return invitation added
	 * @see com.capgemini.services.implementation.InvitationServiceImpl#save(com.capgemini.model.Invitation)
	 */
	@PostMapping("/")
	public <S extends Invitation> S save(@RequestBody  S entity) {
		return invitationServiceImpl.save(entity);
	}
	
	
	/**
	 * 
	 * 
	 * 
	 * @param id
	 * @param userid
	 * @return
	 */
	@PutMapping("/{id}/group{groupid}/user{userid}")
	public TaskGroup acceptInvitation(@PathVariable Long id,@PathVariable Long groupid, @PathVariable Long userid) {
		
		User user = userServiceImpl.findById(userid);
		TaskGroup taskGroup = taskGroupService.findById(groupid);
		
	    invitationServiceImpl.deleteById(id);
		
		
		return invitationServiceImpl.update(taskGroup,user);
		
		
	}
	
	

}
