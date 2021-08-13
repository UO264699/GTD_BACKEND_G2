package com.capgemini.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.capgemini.model.Category;
import com.capgemini.model.Task;
import com.capgemini.model.TaskGroup;
import com.capgemini.model.User;

public interface TaskService {

	/**
	 * @return list of all tasks
	 */
	List<Task> findAll();

	/**
	 * @param id
	 * @return task searched by id
	 */
	Task findById(Long id);
	
	/**
	 * @param taskId
	 * @param taskGroupId
	 * @return task searched by Task id and TaskGroup Id
	 */
	Task findByIdAndTaskGroupId(Long taskId, Long taskGroupId);
	
	/**
	 * @param user
	 * @return list of tasks by user
	 */
	List<Task> findByUser(User user);
	
	/**
	 * @param category
	 * @return list of tasks by category
	 */
	List<Task> findByCategory(Category category);
	
	/**
	 * @param user
	 * @param date
	 * @return list of tasks for pseudolist 'Hoy' for a user
	 */
	List<Task> findByUserTodayTasks(User user, Date date);
	
	/**
	 * @param user
	 * @return list of tasks for pseudolist 'Inbox' for a user
	 */
	List<Task> findByUserInboxTasks(User user);
	
	/**
	 * @param category
	 * @param date
	 * @return list of tasks for pseudolist 'Hoy' for category
	 */
	List<Task> findByCategoryTodayTasks(Category category, Date date);
	
	/**
	 * @param category
	 * @return list of tasks for pseudolist 'Inbox' for category
	 */
	List<Task> findByCategoryInboxTasks(Category category);

	
	/**
	 * @param taskGroup
	 * @return list of tasks linked to a TaskGroup
	 */
	List<Task> findByTaskGroup(TaskGroup taskGroup);
	
	
	/**
	 * @param id
	 * @return list of tasks linked to a TaskGroup Id
	 */
	List<Task> findByTaskGroupId(Long id);
	
	/**
	 * @param task
	 * @return save a task in DB
	 */
	Task save(Task task);

	/**
	 * @param id
	 * delete task by id
	 */
	void deleteById(Long id);

	/**
	 * @param task
	 * delete task by task
	 */
	void delete(Task task);

	/**
	 * delete all tasks
	 */
	void deleteAll();
	
	
	/**
	 * @param task
	 */
	public void finishedTask(Task task);

}
