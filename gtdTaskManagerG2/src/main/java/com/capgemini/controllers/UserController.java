package com.capgemini.controllers;

import java.util.Optional;

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

@RestController
@RequestMapping(path = "/users")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping(path = "/all")
	public @ResponseBody Iterable<User> getAllUsers() {
		return userService.findAll();
	}

	@GetMapping(path = "/{id}")
	public @ResponseBody Optional<User> getUserById(@PathVariable Long id) {
		return userService.findById(id);
	}

	@PostMapping(path = "/add")
	public @ResponseBody String addNewUser(@RequestBody User user) {
		userService.save(user);

		return "Usuario creado con exito";
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
}
