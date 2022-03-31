package com.bootstrap.dao.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class ResetPassword {

	@NotEmpty(message = "{com.bootstrap.dao.NotEmpty.message}")
	@Email(message = "{com.bootstrap.dao.Email.message}")
	private String email;

}
