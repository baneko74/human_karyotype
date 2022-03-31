package com.bootstrap.dao.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.Data;

@Data
public class RegistrationForm {

	private String username;
	private String password;
	private String name;

	@NotEmpty(message = "{com.bootstrap.dao.NotEmpty.message}")
	@Email(message = "{com.bootstrap.dao.Email.message}")
	private String email;

	private String confirm;

	public User toUser(PasswordEncoder encoder) {
		return new User(username, encoder.encode(password), email, name);
	}
}
