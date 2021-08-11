package com.capgemini.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Category {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column
	private String name;
	@Column
	@ManyToOne
	@JoinColumn(name="category_user")
	private User user_id;
	public Category(Long id, String name, User user_id) {
		super();
		this.id = id;
		this.name = name;
		this.user_id = user_id;
	}
	public Category() {
		super();
	}
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
	public User getUser_id() {
		return user_id;
	}
	public void setUser_id(User user_id) {
		this.user_id = user_id;
	}
	@Override
	public int hashCode() {
		return Objects.hash(id, name, user_id);
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
		return Objects.equals(id, other.id) && Objects.equals(name, other.name)
				&& Objects.equals(user_id, other.user_id);
	}
	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", user_id=" + user_id + "]";
	}
	
	

}
