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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;

@Entity
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "name")
	private String name;

	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH})
	@Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
	@JoinColumn(name = "user_id")
	private User user;

	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
	private List<Task> tasks;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public void addTask(Task task) {
		if(tasks == null) {
			tasks = new ArrayList<>();
		}
		tasks.add(task);
		task.setCategory(this);
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", user=" + user + ", tasks=" + tasks + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, tasks, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Category other = (Category) obj;
		return Objects.equals(id, other.id) && Objects.equals(name, other.name) && Objects.equals(tasks, other.tasks)
				&& Objects.equals(user, other.user);
	}
	

}
