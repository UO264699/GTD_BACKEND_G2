package com.capgemini.services.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.model.TaskGroup;
import com.capgemini.repositories.TaskGroupRepository;
import com.capgemini.services.TaskGroupService;

@Service
public class TaskGroupServiceImpl implements TaskGroupService {

	@Autowired
	private TaskGroupRepository taskGroupRepository;

	/**
	 * @param <S>
	 * @param entity
	 * @return
	 * @see org.springframework.data.repository.CrudRepository#save(java.lang.Object)
	 */
	@Override
	public <S extends TaskGroup> S save(S entity) {
		return taskGroupRepository.save(entity);
	}

	/**
	 * @return
	 * @see org.springframework.data.jpa.repository.JpaRepository#findAll()
	 */
	@Override
	public List<TaskGroup> findAll() {
		return taskGroupRepository.findAll();
	}

	/**
	 * @param id
	 * @return
	 * @see org.springframework.data.repository.CrudRepository#findById(java.lang.Object)
	 */
	@Override
	public TaskGroup findById(Long id) {
		Optional<TaskGroup> taskGroupOptional = taskGroupRepository.findById(id);
		if(taskGroupOptional.isEmpty()) {
			throw new IllegalArgumentException("No task group by this id: " + id);
		}
		return taskGroupOptional.get();
	}

	/**
	 * @param entity
	 * @see org.springframework.data.repository.CrudRepository#delete(java.lang.Object)
	 */
	@Override
	public void delete(TaskGroup entity) {
		taskGroupRepository.delete(entity);
	}

	/**
	 * 
	 * @see org.springframework.data.repository.CrudRepository#deleteAll()
	 */
	@Override
	public void deleteAll() {
		taskGroupRepository.deleteAll();
	}
	


}
