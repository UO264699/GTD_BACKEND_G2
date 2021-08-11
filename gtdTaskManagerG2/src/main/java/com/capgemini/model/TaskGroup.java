package com.capgemini.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class TaskGroup {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column
	private String name;
	@Column
	private String description;
	@Column
	private LocalDate creationDate;
	
	@OneToMany(mappedBy = "taskGroup",cascade = CascadeType.ALL)
	private List<Task> tasks;
	
	@ManyToMany
	private List<User> users;
	
	@OneToMany(mappedBy = "taskGroup")
	private List<Invitation> invitations;

	public TaskGroup() {
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	

}
