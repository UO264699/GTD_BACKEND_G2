package com.capgemini;

import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.capgemini.controllers.CategoryController;
import com.capgemini.model.Category;
import com.capgemini.repositories.CategoryRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(CategoryController.class)
class CategoryControllerTest {
	
	@Autowired
	MockMvc mockmvc;
	
	@Autowired
	ObjectMapper mapper;
	
	@MockBean
	CategoryRepository cr;
	
	Category c1= new Category();
	Category c2= new Category();
	Category c3= new Category();
	
	

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testFindAll() throws Exception {
		List<Category> lista=new ArrayList<>();
		lista.add(c1);
		lista.add(c2);
		lista.add(c3);
		
		Mockito.when(cr.findAll()).thenReturn(lista);
		
		mockmvc.perform(MockMvcRequestBuilders.get("/categories/all").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
		
		
	}

	@Test
	void testFindById() throws Exception {
		c1.setId(1L);
		Mockito.when(cr.findById(c1.getId())).thenReturn(java.util.Optional.of(c1));
		
		mockmvc.perform(MockMvcRequestBuilders.get("/categories/1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
		
	}

	@Test
	void testInsertar() throws Exception {
		Category category= new Category();
		category.setId(2L);
		category.setName("categoriaTest");
		category.setTasks(null);
		category.setUser(null);
		
		Mockito.when(cr.save(category)).thenReturn(category);
		
		MockHttpServletRequestBuilder mockRequest=MockMvcRequestBuilders.post("/categories/add").contentType(MediaType.APPLICATION_JSON)
													.accept(MediaType.APPLICATION_JSON)
													.content(this.mapper.writeValueAsString(category));
		
		
		mockmvc.perform(mockRequest)
				.andExpect(status().isOk());
	}

	@Test
	void testModificar() throws Exception {
		Category categoryAct=new Category();
		c2.setId(5L);
		c2.setName("CategoriaTest2");
		c2.setTasks(null);
		c2.setUser(null);
		
		Mockito.when(cr.findById(c2.getId())).thenReturn(Optional.of(c2));
		Mockito.when(cr.save(categoryAct)).thenReturn(categoryAct);
		
		MockHttpServletRequestBuilder mockRequest=MockMvcRequestBuilders.post("/categories/modify/5")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(categoryAct));
		
		mockmvc.perform(mockRequest)
			.andExpect(status().isOk());
	}

	@Test
	void testEliminar() throws Exception {
		Category category= new Category();
		category.setId(7L);
		category.setName("categoriaTest3");
		category.setTasks(null);
		category.setUser(null);
		
		Mockito.when(cr.findById(category.getId())).thenReturn(Optional.of(category));
		
		mockmvc.perform(MockMvcRequestBuilders.delete("/categories/delte/7")
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk());
		
		
	}

}
