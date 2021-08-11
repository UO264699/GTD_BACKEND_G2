package com.capgemini.services;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.model.Category;
import com.capgemini.model.Task;
import com.capgemini.model.User;
import com.capgemini.services.implementation.TaskServiceImpl;

@RestController
@RequestMapping("/tasks")
public class TaskController {

	@Autowired
	private TaskServiceImpl taskService;

	@PostMapping("/")
	public Task addTask(Task task) {
		
		taskService.save(task);
		
		return task;
		
	}
	
	@DeleteMapping("/{id}")
	public String deleteTask(@PathVariable Long id) {
		
		taskService.deleteById(id);
		return "Tarea Borrada";
		
	}
	
	@DeleteMapping("/")
	public String deleteAllTasks() {
		
		taskService.deleteAll();
		return "Tareas Borradas";
		
	}
	
	@GetMapping("/inbox")
	public List<Task> getInboxTasks(HttpSession httpSession) {
		
		User user = (User) httpSession.getAttribute("user");
		
		return taskService.findByUserInboxTasks(user);
	
	}
	
	@GetMapping("/today")
	public List<Task> getTodayTasks(HttpSession httpSession) {
		
		User user = (User) httpSession.getAttribute("user");
		
		return taskService.findByUserTodayTasks(user, new Date());
	
	}
	
	@GetMapping("/category")
	public List<Task> getCategoryTasks(@RequestBody Category category) {
		
	
		
		return taskService.findByCategory(category);
	
	}
	


	
	@PutMapping("/{id}")
	public Task finishedTask(@PathVariable Long id) {
		
		Task task = taskService.findById(id).get();
		
		taskService.finishedTask(task);
		
		return task;
	}
	

}
