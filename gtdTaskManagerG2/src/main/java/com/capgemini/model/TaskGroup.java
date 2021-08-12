package com.capgemini.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class TaskGroup {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String name;
	@Column
	private String description;
	@Column
	private LocalDate creationDate;

	@ManyToOne
	private User admin;

	@OneToMany(mappedBy = "taskGroup",cascade = CascadeType.ALL)
	private List<Task> tasks;
	
	@ManyToMany
	private List<User> users;

	public void addTask(Task task) {
		if(tasks == null) {
			tasks = new ArrayList<>();
		}
		
		tasks.add(task);
		task.setTaskGroup(this);
	}

}
