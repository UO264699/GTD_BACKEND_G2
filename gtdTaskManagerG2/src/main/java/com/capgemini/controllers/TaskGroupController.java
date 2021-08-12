package com.capgemini.controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.model.Task;
import com.capgemini.model.TaskGroup;
import com.capgemini.services.TaskGroupService;
import com.capgemini.services.TaskService;
import com.capgemini.services.UserService;

@RestController
@RequestMapping("/taskgroups")
public class TaskGroupController {
	
	@Autowired
	private TaskGroupService taskGroupService;
	
	@Autowired
	private TaskService taskService;

    @GetMapping(path = "/all")
    public @ResponseBody
    List<TaskGroup> getAllGroupTask() {
        return taskGroupService.findAll();
    }

    @GetMapping(path= "/{id}")
    public @ResponseBody
    Optional<TaskGroup> getTaskGroupById(@PathVariable Long id) {
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
	
	@DeleteMapping("/{id}/tasks{taskId}")
	public ResponseEntity<?> deteleTask(@PathVariable("id") Long id,
										@PathVariable("taskId") Long taskId) {
		taskService.deleteById(taskId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PutMapping("/{id}/finishtask{taskId}")
	public ResponseEntity<?> finishTask(@PathVariable("id") Long id,
										@PathVariable("taskId") Long taskId) {
		Optional<Task> taskOptional = taskService.findByIdAndTaskGroupId(id, taskId);
		if(taskOptional.isPresent()) {
			Task task = taskOptional.get();
			task.setFinished(LocalDate.now());
			return new ResponseEntity<>(taskService.save(task), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	//Pablo
	@Autowired
	UserService userService;
	@DeleteMapping("{id}/user{userId}")
	public ResponseEntity<?> deleteUserFromGroup(@PathVariable("id") Long id, @PathVariable("userId") Long UserId){
		userService.deleteById(UserId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
