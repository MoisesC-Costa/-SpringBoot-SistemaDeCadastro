package me.moises.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Email;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	@Id
	@SequenceGenerator(initialValue = 1000001, allocationSize = 1, name = "idUserGen")
	@GeneratedValue(generator = "idUserGen", strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "username", unique = true)
	private String nome;
	@Email
	@Column(unique = true)
	private String email;
	@Column(name = "userpass")
	private String password;
	@Column(columnDefinition = "char(1) DEFAULT 'I'")
	private String status;
	
	public User(String nome, String pass, String email) {
		this.nome = nome;
		this.password = pass;
		this.email = email;
		this.status = "I";
	}
	
}
