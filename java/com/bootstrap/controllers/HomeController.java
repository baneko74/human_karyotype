package com.bootstrap.controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.validation.Valid;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bootstrap.dao.model.Subscriber;
import com.bootstrap.dao.services.MatchService;
import com.bootstrap.dao.services.SubscriberService;

@Controller
@RequestMapping
public class HomeController {

	private final SubscriberService subService;
	private final MatchService matchService;

	public HomeController(SubscriberService subService, MatchService matchService) {
		this.subService = subService;
		this.matchService = matchService;
	}

	@RequestMapping("/search")
	public String performSearch(Model model, @RequestParam(value = "q", required = false) String query,
			@PageableDefault(page = 0, size = MatchService.DEFAULT_PAGE_SIZE) Pageable pageable) {
		try {
			String lang = LocaleContextHolder.getLocale().getLanguage();
			model.addAttribute("page", matchService.findByTerm(query, pageable, lang));
		} catch (IllegalArgumentException e) {
			model.addAttribute("error", e.getMessage());
		}
		model.addAttribute("date", LocalDateTime.now());
		model.addAttribute("pageable", pageable);
		model.addAttribute("query", query);
		model.addAttribute("subscriber", new Subscriber());
		return "search";
	}

	@GetMapping("/")
	public String getHomePage(Model model) {
		model.addAttribute("subscriber", new Subscriber());
		model.addAttribute("date", LocalDateTime.now());
		return "home";
	}

	@RequestMapping("/subscribe")
	public String processEmailSubscription(@Valid @ModelAttribute Subscriber subscriber, Errors error) {
		if (error.hasErrors()) {
			return "home";
		}
		subscriber.setLang(LocaleContextHolder.getLocale().getLanguage());
		subscriber.setSha1(DigestUtils.sha1Hex(subscriber.getEmail()));
		subService.save(subscriber);
		return "redirect:/";
	}

	@GetMapping("/about")
	public String getAbout(Model model) {
		model.addAttribute("date", LocalDateTime.now());
		model.addAttribute("subscriber", new Subscriber());
		model.addAttribute("date", LocalDate.now());
		return "about";
	}

	@GetMapping("/methodology")
	public String getMethodology(Model model) {
		model.addAttribute("date", LocalDateTime.now());
		model.addAttribute("subscriber", new Subscriber());
		return "methodology";
	}

	@GetMapping("/methodology/dna-extraction")
	public String getDnaExtraction(Model model) {
		model.addAttribute("date", LocalDateTime.now());
		model.addAttribute("subscriber", new Subscriber());
		return "dna-extraction";
	}

	@GetMapping("/methodology/G-banding")
	public String getBanding(Model model) {
		model.addAttribute("date", LocalDateTime.now());
		model.addAttribute("subscriber", new Subscriber());
		return "G-banding";
	}

	@GetMapping("/methodology/fluorescent-in-situ-hybridization")
	public String getFISH(Model model) {
		model.addAttribute("date", LocalDateTime.now());
		model.addAttribute("subscriber", new Subscriber());
		return "fluorescent-in-situ-hybridization";
	}

	@GetMapping("/methodology/polymerase-chain-reaction")
	public String getPCR(Model model) {
		model.addAttribute("date", LocalDateTime.now());
		model.addAttribute("subscriber", new Subscriber());
		return "polymerase-chain-reaction";
	}

	@GetMapping("/karyotype")
	public String getKaryotype(Model model) {
		model.addAttribute("date", LocalDateTime.now());
		model.addAttribute("subscriber", new Subscriber());
		return "karyotype";
	}

	@GetMapping("/cookie")
	public String getCookieInfoPage(Model model) {
		model.addAttribute("date", LocalDateTime.now());
		model.addAttribute("subscriber", new Subscriber());
		return "cookie";
	}
}
