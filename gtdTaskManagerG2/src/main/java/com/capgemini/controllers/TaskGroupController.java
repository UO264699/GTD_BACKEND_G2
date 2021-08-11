package com.capgemini.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.model.Task;
import com.capgemini.model.TaskGroup;
import com.capgemini.services.TaskGroupService;
import com.capgemini.services.TaskService;

@RestController
@RequestMapping("/taskgroups")
public class TaskGroupController {
	
	@Autowired
	private TaskGroupService taskGroupService;

	@Autowired
	private TaskService taskService;
	
	@GetMapping("/{id}/tasks")
	public ResponseEntity<?> findTasks(@PathVariable("id") Long id) {
		return new ResponseEntity<>(taskService.findByTaskGroupId(id), HttpStatus.OK);
	}
	
	@PostMapping("/{id}/tasks")
	public ResponseEntity<?> saveTask(@RequestBody Task task, @PathVariable("id") Long id) {
		Optional<TaskGroup> taskGroupOptional = taskGroupService.findById(id);
		if(taskGroupOptional.isPresent()) {
			TaskGroup taskGroup = taskGroupOptional.get();
			taskGroup.addTask(task);
			return new ResponseEntity<>(taskGroupService.save(taskGroup), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("/{id}/tasks{taskId}")
	public ResponseEntity<?> updateTask(@RequestBody Task task,
										@PathVariable("id") Long id,
										@PathVariable("taskId") Long taskId) {
		Optional<Task> taskOptional = taskService.findByIdAndTaskGroupId(id, taskId);
		if(taskOptional.isPresent()) {
			Task taskTemp = taskOptional.get();
			task.setId(taskTemp.getId());
			return new ResponseEntity<>(taskService.save(task), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

}
