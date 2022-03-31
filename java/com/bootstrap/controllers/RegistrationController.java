package com.bootstrap.controllers;

import java.time.LocalDateTime;

import javax.validation.Valid;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bootstrap.dao.model.Authority;
import com.bootstrap.dao.model.RegistrationForm;
import com.bootstrap.dao.model.ResetPassword;
import com.bootstrap.dao.model.Subscriber;
import com.bootstrap.dao.model.User;
import com.bootstrap.dao.repositories.jpa.UserRepository;
import com.bootstrap.dao.services.ResetPasswordValidator;
import com.bootstrap.dao.services.SubscriberService;
import com.bootstrap.dao.services.UserValidator;

@Controller
@RequestMapping
public class RegistrationController {

	private final UserValidator validator;
	private final UserRepository userRepo;
	private final PasswordEncoder encoder;
	private final SubscriberService subService;
	private final ResetPasswordValidator resetValidator;

	public RegistrationController(UserRepository userRepo, PasswordEncoder encoder, UserValidator validator,
			SubscriberService subService, ResetPasswordValidator resetValidator) {
		this.userRepo = userRepo;
		this.encoder = encoder;
		this.validator = validator;
		this.subService = subService;
		this.resetValidator = resetValidator;
	}

	@GetMapping("/admin/register")
	public String registerForm(Model model) {
		model.addAttribute("subscriber", new Subscriber());
		model.addAttribute("data", new RegistrationForm());
		return "registration";
	}

	@PostMapping("/admin/register")
	public String processRegistration(Model model, @Valid @ModelAttribute("data") RegistrationForm formData,
			Errors errors) {
		validator.validate(formData, errors);
		if (errors.hasErrors()) {
			model.addAttribute("subscriber", new Subscriber());
			return "registration";
		}
		User user = formData.toUser(encoder);
		Authority authority = new Authority("USER");
		user.addAuthority(authority);
		userRepo.save(user);
		subService.save(makeSubscriber(formData));
		return "redirect:/admin/login";
	}

	private Subscriber makeSubscriber(@ModelAttribute("data") RegistrationForm formData) {
		Subscriber sub = new Subscriber();
		String lang = LocaleContextHolder.getLocale().getLanguage();
		sub.setLang(lang);
		String sha1 = DigestUtils.sha1Hex(formData.getEmail());
		sub.setSha1(sha1);
		sub.setEmail(formData.getEmail());
		return sub;
	}

	@GetMapping("/admin/login")
	public String getLoginPage(Model model) {
		model.addAttribute("date", LocalDateTime.now());
		model.addAttribute("subscriber", new Subscriber());
		return "login";
	}

	@GetMapping("/accessDenied")
	public String accessDenied() {
		return "accessDenied";
	}

	@GetMapping("/admin/reset-password")
	public String newPassword(Model model) {
		model.addAttribute("resetpassword", new ResetPassword());
		model.addAttribute("subscriber", new Subscriber());
		return "new-password";
	}

	@PostMapping("/admin/reset-password")
	public String processNewPasswordRequest(Model model, @Valid @ModelAttribute("resetpassword") ResetPassword email,
			Errors errors) {
		resetValidator.validate(email, errors);
		if (errors.hasErrors()) {
			model.addAttribute("reset", new ResetPassword());
			model.addAttribute("subscriber", new Subscriber());
			return "new-password";
		}

		/*
		 * Send email with a link to new web form for password change.
		 */

		return "redirect:/admin/login";

	}
}