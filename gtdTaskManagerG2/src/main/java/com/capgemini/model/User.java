package com.capgemini.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "is_admin")
	private boolean isAdmin;
	
	@Column(name = "login")
	private String login;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "status")
	private String status;
	
	@Transient
	private String password2;


	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Category> categories;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Task> tasks;


	@OneToMany(mappedBy = "admin", cascade = CascadeType.ALL)
	private List<TaskGroup> adminList;


	@ManyToMany
	private List<TaskGroup> taskGroups;

	
	@OneToMany(mappedBy = "user")
	private List<Invitation> invitations;
	
	
	public void addCategory(Category category) {
		if(categories == null) {
			categories = new ArrayList<>();
		}
		categories.add(category);
		category.setUser(this);
	}
	public void addTask(Task task) {
		if(tasks == null) {
			tasks = new ArrayList<>();
		}
		tasks.add(task);
		task.setUser(this);
	}

}
