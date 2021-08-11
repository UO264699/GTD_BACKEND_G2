package com.capgemini.controllers;

import com.capgemini.model.Task;
import com.capgemini.model.TaskGroup;

import com.capgemini.services.implementation.TaskGroupServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/taskgroups")
public class TaskGroupController {

    @Autowired
    private TaskGroupServiceImpl taskGroupService;

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

}
