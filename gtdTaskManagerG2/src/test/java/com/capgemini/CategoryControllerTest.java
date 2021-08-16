package com.capgemini;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.capgemini.model.Category;
import com.capgemini.model.User;
import com.capgemini.services.CategoryService;
import com.capgemini.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class CategoryControllerTest {
	
	@Autowired
	MockMvc mockmvc;
	
	@Autowired
	ObjectMapper mapper;
	
	@Autowired
	UserService us;
	
	
	@MockBean
	CategoryService cs;
	
	Category c1= new Category();
	Category c2= new Category();
	Category c3= new Category();
	
	

	@BeforeEach
	void setUp() throws Exception {
	
	}

	@Test
	void testFindAll() throws Exception {
		List<Category> lista=new ArrayList<>();
		
		c1.setId(1L);
		c1.setName("Categoria1");
		c1.setTasks(new ArrayList<>());
		c1.setUser(new User());
		
		c2.setId(2L);
		c2.setName("Categoria2");
		c2.setTasks(new ArrayList<>());
		c2.setUser(new User());
		
		c3.setId(3L);
		c3.setName("Categoria3");
		c3.setTasks(new ArrayList<>());
		c3.setUser(new User());
		
		lista.add(c1);
		lista.add(c2);
		lista.add(c3);
		
		Mockito.when(cs.findAll()).thenReturn(lista);
		
		mockmvc.perform(MockMvcRequestBuilders.get("/categories/all").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
		
		
	}

	@Test
	void testFindById() throws Exception {
		c1.setId(1L);
		Mockito.when(cs.findById(c1.getId())).thenReturn(c1);
		
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
		
		Mockito.when(cs.save(category)).thenReturn(category);
		
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
		
		Mockito.when(cs.findById(c2.getId())).thenReturn(c2);
		Mockito.when(cs.save(categoryAct)).thenReturn(categoryAct);
		
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
		
		Mockito.when(cs.findById(category.getId())).thenReturn(category);
		
		mockmvc.perform(MockMvcRequestBuilders.delete("/categories/delte/7")
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk());
		
		
	}

}
