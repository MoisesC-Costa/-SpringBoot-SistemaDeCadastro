package me.moises.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import me.moises.dto.UserForm;
import me.moises.model.entity.User;
import me.moises.service.*;

@RestController
@RequestMapping("/usuarios")
public class UserController {
	
	@Autowired
	UserService userService;
	@Autowired
	ChecklistService checklistService;
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<User> saveUser(@Valid @RequestBody final UserForm user){
		return userService.save(user);
	}
	
	@GetMapping
	public ResponseEntity<List<User>> listUsers(){
		return userService.list();
	}
	
	@PutMapping("{id}")
	public ResponseEntity<User> updateUser(@Valid @PathVariable final Long id, @RequestBody UserForm user){
		return userService.update(id, user);
	}
	
	@PostMapping("/ativar/{uuid}")
	public ResponseEntity<Void> activeUser(@PathVariable final String uuid){
		return checklistService.active(uuid);
	}
}
