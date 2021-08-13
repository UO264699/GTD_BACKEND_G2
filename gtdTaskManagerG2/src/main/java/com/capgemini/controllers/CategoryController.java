package com.capgemini.controllers;

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
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.model.Category;
import com.capgemini.services.CategoryService;

@RestController
@RequestMapping(path="/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@GetMapping(path="/all")
	public ResponseEntity<?> findAll(){
		List<Category> lista=categoryService.findAll();
		if(lista.isEmpty())
			return ResponseEntity.notFound().build();
		else return ResponseEntity.ok(lista);
	}
	
	@GetMapping(path="/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id){
		Category category=categoryService.findById(id);
		return ResponseEntity.ok(category);

	}
	
	@PostMapping(path="/add")
	public ResponseEntity<?> insertar(@RequestBody Category category){
		categoryService.save(category);
		return ResponseEntity.status(HttpStatus.CREATED).body(category);
	}
	
	@PutMapping(path="/modify/{id}")
	public ResponseEntity<?> modificar(@PathVariable Long id, @RequestBody Category category){
		Category categoryTemp = categoryService.findById(id);
		category.setId(categoryTemp.getId());
		return ResponseEntity.ok(categoryService.save(category));
	}
	
	@DeleteMapping(path="/delte/{id}")
	public ResponseEntity<?> eliminar(@PathVariable Long id){
		Category category = categoryService.findById(id);
		categoryService.delete(category);
		return ResponseEntity.ok(category);
	}
	

}
