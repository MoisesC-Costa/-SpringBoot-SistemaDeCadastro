package me.moises.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import me.moises.advice.RegraNegocioException;
import me.moises.dto.AuthenticatedUser;
import me.moises.dto.LoginForm;
import me.moises.model.entity.User;
import me.moises.model.repository.UserRepository;

@Service
public class LoginService {
	@Autowired
	private UserRepository userRepository;
	
	public ResponseEntity<AuthenticatedUser> login(LoginForm loginForm){
		return ResponseEntity.accepted().body(new AuthenticatedUser(
				findUser(loginForm).getNome(), loginForm.getEmail(), UUID.randomUUID().toString()));
	}
	
	private User findUser(LoginForm loginForm) {
		Optional<User> queryResult = userRepository.findByEmailAndPassword(
				loginForm.getEmail(), loginForm.getPassword());
		
		if (queryResult.isEmpty())
			throw new RegraNegocioException("Email ou Senha invalidos!");
		
		User user = queryResult.get();
		if (user.getStatus().equals("I"))
			throw new RegraNegocioException("Usuario não ativado, por favor verifique seu E-Mail!");
		return user;
	}
	
}
