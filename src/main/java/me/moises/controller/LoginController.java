package me.moises.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import me.moises.dto.AuthenticatedUser;
import me.moises.dto.LoginForm;
import me.moises.service.LoginService;

@RestController
@RequestMapping("/login")
public class LoginController {
	@Autowired
	private LoginService loginService;
	
	
	@PostMapping
	public ResponseEntity<AuthenticatedUser> userLogin(@RequestBody final LoginForm loginForm){
		return loginService.login(loginForm);
	}
	
}
