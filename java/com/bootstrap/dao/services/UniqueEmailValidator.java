package com.bootstrap.dao.services;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

	@Autowired
	private SubscriberService subscriberService;

	@Override
	public boolean isValid(String email, ConstraintValidatorContext context) {
		return email != null && !subscriberService.findByEmail(email).isPresent();
	}

}
