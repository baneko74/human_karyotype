package com.bootstrap.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootstrap.dao.model.AjaxResponse;
import com.bootstrap.dao.model.Subscriber;
import com.bootstrap.dao.services.SubscriberService;

@Controller
public class SubscriberAjaxController {

	private final SubscriberService subService;

	private final MessageSource messageSource;

	public SubscriberAjaxController(SubscriberService subService, MessageSource messageSource) {
		this.messageSource = messageSource;
		this.subService = subService;
	}

	@ResponseBody
	@PostMapping(path = "/subscribeEmail", consumes = MediaType.APPLICATION_JSON_VALUE, headers = {
			"Accept=application/json", "Content-Type=application/json" })
	public ResponseEntity<AjaxResponse> getAjaxSubcsription(@Valid @RequestBody Subscriber subscriber, Errors errors) {
		if (errors.hasErrors()) {
			return new ResponseEntity<>(
					AjaxResponse.builder().status("error").message(errors.getFieldError().getDefaultMessage()).build(),
					HttpStatus.BAD_REQUEST);
		}
		String lang = LocaleContextHolder.getLocale().getLanguage();
		String sha1 = DigestUtils.sha1Hex(subscriber.getEmail());
		subscriber.setLang(lang);
		subscriber.setSha1(sha1);
		subService.save(subscriber);
		return new ResponseEntity<>(AjaxResponse.builder().status("success")
				.message(messageSource.getMessage("ajax.message", null, LocaleContextHolder.getLocale())).build(),
				HttpStatus.CREATED);
	}

	@GetMapping("/unsubscribe")
	public String unsubscribe(@RequestParam("code") String code, Model model) {
		Optional<Subscriber> sub = subService.findBySha1(code);
		String answer = messageSource.getMessage("unsubscribe.already", null, LocaleContextHolder.getLocale());
		if (sub.isPresent()) {
			Subscriber subscriber = sub.get();
			subService.delete(subscriber);
			String lang = subscriber.getLang();
			switch (lang) {
			case "en":
				answer = "You were successfully unsubscribed. We are sorry seeing you go.";
				break;
			case "rs":
				answer = "Uspešno ste se odjavili. Žao nam je što odlazite.";
			}
		}
		model.addAttribute("answer", answer);
		return "unsubscribe";

	}
}
