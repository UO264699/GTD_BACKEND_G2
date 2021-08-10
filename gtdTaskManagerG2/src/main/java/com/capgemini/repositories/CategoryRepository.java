package com.capgemini.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

}
