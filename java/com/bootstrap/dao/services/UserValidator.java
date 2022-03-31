package com.bootstrap.dao.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.bootstrap.dao.model.RegistrationForm;
import com.bootstrap.dao.repositories.jpa.UserRepository;

@Component
public class UserValidator implements Validator {

	@Autowired
	private UserRepository userRepo;

	@Override
	public boolean supports(Class<?> clazz) {
		return RegistrationForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		RegistrationForm form = (RegistrationForm) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "registration.form.NotEmpty.name.message");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "registration.form.NotEmpty.username.message");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "registration.form.NotEmpty.password.message");
		if (!form.getPassword().equals(form.getConfirm())) {
			errors.rejectValue("confirm", "registration.form.confirm.notsame");
		} else if (userRepo.findByUsername(form.getUsername()) != null) {
			errors.rejectValue("username", "registration.form.Duplicate.username.message");
		} else if (userRepo.findByEmail(form.getEmail()).isPresent()) {
			errors.rejectValue("email", "registration.form.email.in.use");
		}

	}

}