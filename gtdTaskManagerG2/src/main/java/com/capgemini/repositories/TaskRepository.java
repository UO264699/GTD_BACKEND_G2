package com.capgemini.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long>{

}
