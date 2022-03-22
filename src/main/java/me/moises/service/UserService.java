package me.moises.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import me.moises.advice.RegraNegocioException;
import me.moises.dto.UserForm;
import me.moises.model.entity.Checklist;
import me.moises.model.entity.User;
import me.moises.model.repository.ChecklistRepository;
import me.moises.model.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;	
	@Autowired
	private ChecklistRepository checkListrepository;
	@Autowired
	private ChecklistService checklistService;
	
	@Transactional
	public ResponseEntity<User> save(UserForm user){
		if(userRepository.findByEmail(user.getEmail()).isPresent())
			throw new RegraNegocioException("Email já utilizado");
		
		User userDB = userRepository.save(
				new User(user.getUsername(), user.getPassword(), user.getEmail()));
		checklistService.sendEmail(checkListrepository.save(new Checklist(userDB)));
		return ResponseEntity.ok().body(userDB);
	}
	
	public ResponseEntity<List<User>> list(){
		return ResponseEntity.ok().body(userRepository.findAll());
	}
	
	@Transactional
	public ResponseEntity<User> update(Long id, UserForm user){
		Optional<User> userDB = userRepository.findById(id);
		if(userDB.isPresent()) {
			User userRS = userDB.get();
			userRS.setId(id);
			userRS.setNome(user.getUsername());
			userRS.setEmail(user.getEmail());
			userRS.setPassword(user.getPassword());
			return ResponseEntity.ok(userRepository.save(userRS));
		}
		
		throw new RegraNegocioException("Usuario não existe");
	}
}
