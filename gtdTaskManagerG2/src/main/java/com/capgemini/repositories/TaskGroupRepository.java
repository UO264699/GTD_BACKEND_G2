package com.capgemini.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.model.TaskGroup;

public interface TaskGroupRepository extends JpaRepository<TaskGroup, Long> {

}
