package com.capgemini.services.implementation;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.model.Category;
import com.capgemini.model.Task;
import com.capgemini.model.User;
import com.capgemini.repositories.TaskRepository;
import com.capgemini.services.TaskService;

@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
	private TaskRepository taskRepository;

	@Override
	public List<Task> findAll() {
		return taskRepository.findAll();
	}

	@Override
	public Optional<Task> findById(Long id) {
		return taskRepository.findById(id);
	}
	
	@Override
	public List<Task> findByUser(User user) {
		return taskRepository.findByUser(user);
	}
	
	@Override
	public List<Task> findByCategory(Category category) {
		return taskRepository.findByCategory(category);
	}
	
	@Override
	public List<Task> findByUserTodayTasks(User user, Date date) {
		return taskRepository.findByUserAndPlannedLessThanEqualOrderByPlannedAsc(user, date);
	}

	@Override
	public List<Task> findByUserInboxTasks(User user) {
		return taskRepository.findByUserAndPlannedIsNullOrderByCreatedAsc(user);
	}
	
	@Override
	public List<Task> findByCategoryTodayTasks(Category category, Date date) {
		return taskRepository.findByCategoryAndPlannedLessThanEqualOrderByPlannedAsc(category, date);
	}

	@Override
	public List<Task> findByCategoryInboxTasks(Category category) {
		return taskRepository.findByCategoryAndPlannedIsNullOrderByCreatedAsc(category);
	}

	@Override
	public Task save(Task task) {
		return taskRepository.save(task);
	}

	@Override
	public void deleteById(Long id) {
		taskRepository.deleteById(id);
	}

	@Override
	public void delete(Task task) {
		taskRepository.delete(task);
	}

	@Override
	public void deleteAll() {
		taskRepository.deleteAll();
	}

}
