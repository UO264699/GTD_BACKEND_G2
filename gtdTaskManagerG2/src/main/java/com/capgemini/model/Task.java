package com.capgemini.model;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Cascade;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "comments")
	private String comments;
	
	@Column(name = "created")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate created;
	
	@Column(name = "finished")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate finished;
	
	@Column(name = "planned")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate planned;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "observations")
	private String observations;
	
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH})
	@Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
	@JoinColumn(name = "user_id")
	private User user;
	
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH})
	@Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
	@JoinColumn(name = "category_id")
	private Category category;
	
	@ManyToOne
	private TaskGroup taskGroup;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public LocalDate getCreated() {
		return created;
	}
	public void setCreated(LocalDate created) {
		this.created = created;
	}
	public LocalDate getFinished() {
		return finished;
	}
	public void setFinished(LocalDate finished) {
		this.finished = finished;
	}
	public LocalDate getPlanned() {
		return planned;
	}
	public void setPlanned(LocalDate planned) {
		this.planned = planned;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getObservations() {
		return observations;
	}
	public void setObservations(String observations) {
		this.observations = observations;
	}
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	@Override
	public String toString() {
		return "Task [id=" + id + ", comments=" + comments + ", created=" + created + ", finished=" + finished
				+ ", planned=" + planned + ", title=" + title + ", observations=" + observations + ", user=" + user
				+ ", category=" + category + "]";
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(category, comments, created, finished, id, observations, planned, title, user);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Task other = (Task) obj;
		return Objects.equals(category, other.category) && Objects.equals(comments, other.comments)
				&& Objects.equals(created, other.created) && Objects.equals(finished, other.finished)
				&& Objects.equals(id, other.id) && Objects.equals(observations, other.observations)
				&& Objects.equals(planned, other.planned) && Objects.equals(title, other.title)
				&& Objects.equals(user, other.user);
	}

}
