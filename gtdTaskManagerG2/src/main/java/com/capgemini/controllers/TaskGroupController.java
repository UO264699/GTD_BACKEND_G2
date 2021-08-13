package com.capgemini.controllers;

import com.capgemini.model.*;

import com.capgemini.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
	private UserService userService;
	
	@Autowired
	private TaskService taskService;

    @GetMapping(path = "/all")
    public @ResponseBody
    List<TaskGroup> getAllGroupTask() {
        return taskGroupService.findAll();
    }

    @GetMapping(path= "/{id}")
    public @ResponseBody
    TaskGroup getTaskGroupById(@PathVariable Long id) {
        return taskGroupService.findById(id);
    }

    @PostMapping(path = "/add/{taskGroup}")
    public @ResponseBody
    TaskGroup createNewGroupTask(@RequestBody TaskGroup taskGroup) {
        return taskGroupService.save(taskGroup);
    }

    @DeleteMapping(path = "/delete/{taskGroup}")
    public @ResponseBody
    TaskGroup deleteGroupTask(@RequestBody TaskGroup taskGroup) {
        taskGroupService.delete(taskGroup);

        return taskGroup;
    }

    @DeleteMapping(path = "/deleteAll")
    public @ResponseBody
    String deleteAllGroupTask() {
        taskGroupService.deleteAll();

        return "Deleted all taskGroups";
    }

    @PutMapping(path = "/update/{taskGroup}")
    public @ResponseBody TaskGroup updateTaskGroup(@RequestBody TaskGroup taskGroup) {

        TaskGroup newTaskGroup = new TaskGroup();
        newTaskGroup.setId(taskGroup.getId());
        newTaskGroup.setName(taskGroup.getName());
        newTaskGroup.setTasks(taskGroup.getTasks());
        newTaskGroup.setCreationDate(taskGroup.getCreationDate());
        newTaskGroup.setDescription(taskGroup.getDescription());
        newTaskGroup.setUsers(taskGroup.getUsers());

        taskGroupService.save(newTaskGroup);

        return newTaskGroup;
    }
	
	@GetMapping("/{id}/tasks")
	public ResponseEntity<?> findTasks(@PathVariable("id") Long id) {
		return new ResponseEntity<>(taskService.findByTaskGroupId(id), HttpStatus.OK);
	}
	
	@PostMapping("/{id}/tasks")
	public ResponseEntity<?> saveTask(@RequestBody Task task, @PathVariable("id") Long id) {
		TaskGroup taskGroup = taskGroupService.findById(id);
		return new ResponseEntity<>(taskGroupService.save(taskGroup), HttpStatus.OK);
	}
	
	@PutMapping("/{id}/tasks/{taskId}")
	public ResponseEntity<?> updateTask(@RequestBody Task task,
										@PathVariable("id") Long id,
										@PathVariable("taskId") Long taskId) {
		Task taskTemp = taskService.findByIdAndTaskGroupId(id, taskId);
		task.setId(taskTemp.getId());
		return new ResponseEntity<>(taskService.save(task), HttpStatus.OK);

	}
	
	@DeleteMapping("/{id}/tasks/{taskId}")
	public ResponseEntity<?> deteleTask(@PathVariable("id") Long id,
										@PathVariable("taskId") Long taskId) {
		Task task = taskService.findByIdAndTaskGroupId(id, taskId);
		taskService.delete(task);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PutMapping("/{id}/tasks/{taskId}")
	public ResponseEntity<?> finishTask(@PathVariable("id") Long id,
										@PathVariable("taskId") Long taskId) {
		Task task = taskService.findByIdAndTaskGroupId(id, taskId);
		task.setFinished(LocalDate.now());
		return new ResponseEntity<>(taskService.save(task), HttpStatus.OK);

	}

	@PutMapping("/updateStatus/{id}/{idUser}")
	public String updateStatus(@PathVariable Long id, @PathVariable Long idUser) {
    	TaskGroup taskGroup = taskGroupService.findById(id);

    	User user = userService.findById(idUser);
    	
    	taskGroup.setAdmin(user);

    	return "Usuario Cambiado";
	}
}
