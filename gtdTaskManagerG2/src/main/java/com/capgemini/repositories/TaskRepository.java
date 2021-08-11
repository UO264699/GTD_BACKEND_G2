package com.capgemini.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.model.Category;
import com.capgemini.model.Task;
import com.capgemini.model.TaskGroup;
import com.capgemini.model.User;

public interface TaskRepository extends JpaRepository<Task, Long>{

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
	 * @return list of tasks by user and planned field less or equal to date and ascending order
	 */
	List<Task> findByUserAndPlannedLessThanEqualOrderByPlannedAsc(User user, Date date);
	/**
	 * @param user
	 * @return list of tasks by user and planned field null and ascending order
	 */
	List<Task> findByUserAndPlannedIsNullOrderByCreatedAsc(User user);
	/**
	 * @param category
	 * @param date
	 * @return list of tasks by category and planned field less or equal to date and ascending order
	 */
	List<Task> findByCategoryAndPlannedLessThanEqualOrderByPlannedAsc(Category category, Date date);
	/**
	 * @param category
	 * @return list of tasks by category and planned field null and ascending order
	 */
	List<Task> findByCategoryAndPlannedIsNullOrderByCreatedAsc(Category category);
	/**
	 * @param taskGroup
	 * @return list of tasks by TaskGroup
	 */
	List<Task> findByTaskGroup(TaskGroup taskGroup);
	/**
	 * @param id
	 * @return list of tasks by TaskGroup Id
	 */
	List<Task> findByTaskGroupId(Long id);
}
