package com.bootstrap.dao.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.bootstrap.dao.model.ResetPassword;
import com.bootstrap.dao.repositories.jpa.UserRepository;

@Component
public class ResetPasswordValidator implements Validator {

	@Autowired
	private UserRepository userRepo;

	@Override
	public boolean supports(Class<?> clazz) {
		return ResetPassword.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ResetPassword obj = (ResetPassword) target;

		if (!userRepo.findByEmail(obj.getEmail()).isPresent()) {
			errors.rejectValue("email", "reset.password.email.not.found");
		}
	}

}
