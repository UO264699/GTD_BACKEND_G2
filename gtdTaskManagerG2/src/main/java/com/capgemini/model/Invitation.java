package com.capgemini.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Invitation {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne
	private User user;
	
	@ManyToOne
	private TaskGroup taskGroup;

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @param taskGroup the taskGroup to set
	 */
	public void setTaskGroup(TaskGroup taskGroup) {
		this.taskGroup = taskGroup;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return the taskGroup
	 */
	public TaskGroup getTaskGroup() {
		return taskGroup;
	}

	public Invitation() {
		// TODO Auto-generated constructor stub
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
