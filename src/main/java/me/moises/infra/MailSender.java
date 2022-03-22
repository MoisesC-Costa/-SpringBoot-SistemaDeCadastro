package me.moises.infra;


import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

public class MailSender {

	private JavaMailSender javaMailSender;
	
	public MailSender(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}
	
	public void sendMail(String destiny, String text, String subject) {
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		
		try {
			helper.setSubject(subject);
			helper.setTo(destiny);
			helper.setText("<h1>Utilize esse token para ativar a sua conta</h1>"
					+"<p>" + text + "</p>", true);
			javaMailSender.send(message);

		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
