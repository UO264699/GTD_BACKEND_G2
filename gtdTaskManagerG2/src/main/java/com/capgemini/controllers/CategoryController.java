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
@RequestMapping
public class CategoryController {

	@Autowired
	CategoryService cs;
	
	@GetMapping("/")
	public ResponseEntity<?> findAll(){
		List<Category> lista=cs.findAll();
		if(lista.isEmpty())
			return ResponseEntity.notFound().build();
		else return ResponseEntity.ok(lista);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id){
		Optional<Category> cg=cs.findById(id);
		if(cg.isPresent())
			return ResponseEntity.ok(cg.get());
		else return ResponseEntity.notFound().build();
	}
	
	@PostMapping("/")
	public ResponseEntity<?> insertar(@RequestBody Category category){
		cs.save(category);
		return ResponseEntity.status(HttpStatus.CREATED).body(category);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> modificar(@PathVariable Long id, @RequestBody Category category){
		if(cs.findById(id).isPresent()) {
			category.setId(id);
			return ResponseEntity.ok(cs.save(category));
		}else return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminar(@PathVariable Long id){
		if(cs.findById(id).isPresent()) {
			Category c=cs.findById(id).get();
			cs.delete(c);
			return ResponseEntity.ok(c);
		}else return ResponseEntity.notFound().build();
	}
	

}
