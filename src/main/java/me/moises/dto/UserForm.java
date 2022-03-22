package me.moises.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class UserForm {
	@NotNull
	private String username;
	@NotNull
	private String password;
	@NotNull
	private String email;
}
