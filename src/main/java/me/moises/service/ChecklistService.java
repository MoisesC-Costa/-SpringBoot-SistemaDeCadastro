package me.moises.service;

import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import me.moises.advice.RegraNegocioException;
import me.moises.infra.MailSender;
import me.moises.model.entity.Checklist;
import me.moises.model.entity.User;
import me.moises.model.repository.ChecklistRepository;
import me.moises.model.repository.UserRepository;

@Service
public class ChecklistService {
	
	@Autowired
	ChecklistRepository checklistRepository;
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	private JavaMailSender sender;
	private MailSender mailSender;
	
	@PostConstruct
	public void loadObjects() {
		this.mailSender = new MailSender(sender);
	}
	
	@Async
	public void sendEmail(final Checklist checklist) {
		mailSender.sendMail(checklist.getUser().getEmail(), checklist.getToken(), "Criação de Conta");
	}
	
	@Transactional
	public ResponseEntity<Void> active(final String uuid) {
		Optional<Checklist> queryResult = checklistRepository.findByToken(uuid);
		
		if (queryResult.isPresent()) {
			User user = queryResult.get().getUser();
			user.setStatus("A");
			userRepository.save(user);
			checklistRepository.delete(queryResult.get());
			return ResponseEntity.ok(null);
		} else
		throw new RegraNegocioException("Token [" + uuid + "] não é valido");
	}
}
