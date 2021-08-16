package com.capgemini.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.model.User;
import com.capgemini.services.UserService;

import com.capgemini.services.security.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	
	@Autowired
	private SecurityService securityService;

	@GetMapping(path = "/all")
	public @ResponseBody Iterable<User> getAllUsers() {
		return userService.findAll();
	}

	@GetMapping(path = "/{id}")
	public @ResponseBody User getUserById(@PathVariable Long id) {
		return userService.findById(id);
	}

	@PostMapping(path = "/add")
	public @ResponseBody User addNewUser(@RequestBody User user) {
		
		userService.save(user);
		
		securityService.autoLogin(user.getLogin(), user.getPassword2());
	
		return user;
	}

	@DeleteMapping(path = "/delete/{id}")
	public @ResponseBody String deleteUser(@PathVariable Long id) {
		userService.deleteById(id);

		return "Usuario eliminado";
	}

	@DeleteMapping(path = "/delete")
	public @ResponseBody String deleteUser(@RequestBody User user) {
		userService.delete(user);

		return "Usuario eliminado";
	}

	@DeleteMapping(path = "/deleteAll")
	public @ResponseBody String deleteAllUsers() {
		userService.deleteAll();

		return "Todos los usuarios fueron eliminados";
	}

	@GetMapping(path = "/{login}")
	public @ResponseBody User getUserByLogin(@PathVariable String login) {
		return userService.findByLogin(login);
	}
	

	
//	//Pablo
//	@PutMapping("/{id}")
//	public ResponseEntity<?> setAdmin(@PathVariable Long id, @RequestBody User user){
//		if(userService.findById(id).isPresent()) {
//			user.setAdmin(true);
//			return ResponseEntity.ok(userService.save(user));
//		}else return ResponseEntity.notFound().build();
//	}
//	//Pablo
//	@DeleteMapping("/{id}/group{groupId}")
//	public ResponseEntity<?> deleteUserFromGroup(@PathVariable("id") Long Id, @PathVariable("groupId") Long groupId){
//		userService.deleteById(groupId);
//		return new ResponseEntity<>(HttpStatus.OK);
//	}
	

	@GetMapping(path = "/login")
	public @ResponseBody String login() {
		return "login";
	}

}
