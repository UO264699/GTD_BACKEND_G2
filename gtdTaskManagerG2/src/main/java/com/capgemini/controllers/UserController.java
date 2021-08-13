package com.capgemini.controllers;

import com.capgemini.model.User;
import com.capgemini.services.UserService;
import com.capgemini.services.security.SecurityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
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
	public @ResponseBody Optional<User> getUserById(@PathVariable Long id) {
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
	
	@GetMapping(path = "/login")
	public @ResponseBody String login() {
		return "login";
	}
}
