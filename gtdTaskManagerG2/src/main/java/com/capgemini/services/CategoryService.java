package com.capgemini.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.capgemini.model.Category;

@Service
public interface CategoryService {

	<S extends Category> S save(S entity);

	<S extends Category> Optional<S> findOne(Example<S> example);

	Page<Category> findAll(Pageable pageable);

	List<Category> findAll();

	List<Category> findAll(Sort sort);

	List<Category> findAllById(Iterable<Long> ids);

	Optional<Category> findById(Long id);

	<S extends Category> List<S> saveAll(Iterable<S> entities);

	void flush();

	<S extends Category> S saveAndFlush(S entity);

	boolean existsById(Long id);

	<S extends Category> List<S> saveAllAndFlush(Iterable<S> entities);

	<S extends Category> Page<S> findAll(Example<S> example, Pageable pageable);

	void deleteInBatch(Iterable<Category> entities);

	<S extends Category> long count(Example<S> example);

	<S extends Category> boolean exists(Example<S> example);

	void deleteAllInBatch(Iterable<Category> entities);

	long count();

	void deleteById(Long id);

	void deleteAllByIdInBatch(Iterable<Long> ids);

	void delete(Category entity);

	void deleteAllById(Iterable<? extends Long> ids);

	void deleteAllInBatch();

	Category getOne(Long id);

	void deleteAll(Iterable<? extends Category> entities);

	void deleteAll();

	Category getById(Long id);

	<S extends Category> List<S> findAll(Example<S> example);

	<S extends Category> List<S> findAll(Example<S> example, Sort sort);

}