package com.capgemini.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
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
	
	public String getPassword2() {
		return password2;
	}
	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Category> categories;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Task> tasks;
	
	@ManyToMany
	private List<TaskGroup> taskGroups;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isAdmin() {
		return isAdmin;
	}
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<Category> getCategories() {
		return categories;
	}
	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
	public List<Task> getTasks() {
		return tasks;
	}
	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}
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
	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", isAdmin=" + isAdmin + ", login=" + login + ", password="
				+ password + ", status=" + status + ", password2=" + password2 + ", categories=" + categories
				+ ", tasks=" + tasks + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(categories, email, id, isAdmin, login, password, password2, status, tasks);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(categories, other.categories) && Objects.equals(email, other.email)
				&& Objects.equals(id, other.id) && isAdmin == other.isAdmin && Objects.equals(login, other.login)
				&& Objects.equals(password, other.password) && Objects.equals(password2, other.password2)
				&& Objects.equals(status, other.status) && Objects.equals(tasks, other.tasks);
	}

}
