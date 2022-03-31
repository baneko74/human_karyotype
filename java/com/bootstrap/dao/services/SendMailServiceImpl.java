package com.bootstrap.dao.services;

import java.time.LocalDateTime;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.bootstrap.dao.model.Subscriber;

@Service("sendMailService")
@Transactional
public class SendMailServiceImpl implements SendMailService {

	private final JavaMailSender mailSender;
	private final TemplateEngine engine;
	private final MessageSource msgSource;

	public SendMailServiceImpl(JavaMailSender mailSender, TemplateEngine engine, MessageSource msgSource) {
		this.mailSender = mailSender;
		this.engine = engine;
		this.msgSource = msgSource;
	}

	@Override
	public void sendEmail(String subject, String text, String email) {
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(email);
		msg.setSubject(subject);
		msg.setText(text);
		mailSender.send(msg);
	}

	@Override
	public void sendRichMail(Subscriber subscriber, String subject, String body) {
		MimeMessagePreparator preparator = mimeMsg -> {
			MimeMessageHelper helper = new MimeMessageHelper(mimeMsg, true);
			helper.setFrom("admin@humankaryotype.com");
			helper.setTo(subscriber.getEmail());
			helper.setSubject(subject);
			String content = build(body, subscriber.getSha1());
			helper.setText(content, true);
			helper.addInline("logo", new ClassPathResource("static/images/logo_1.png"), "image/png");
		};
		try {
			mailSender.send(preparator);
		} catch (MailException e) {
			e.getMessage();
		}
	}

	private String build(String message, String code) {
		Context ctx = new Context();
		LocalDateTime date = LocalDateTime.now();
		String unSubLink = "https://humankaryotype.com/unsubscribe?code=" + code;
		String title = msgSource.getMessage("email.title", null, LocaleContextHolder.getLocale());
		ctx.setVariable("title", title);
		ctx.setVariable("logo", "logo");
		ctx.setVariable("unsubscribe", unSubLink);
		ctx.setVariable("date", date);
		ctx.setVariable("message", message);
		return engine.process("mailTemplate", ctx);
	}
}
