package com.capgemini.model;

import java.time.LocalDate;

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

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
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

}
