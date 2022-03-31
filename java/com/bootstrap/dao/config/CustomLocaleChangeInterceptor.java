package com.bootstrap.dao.config;

import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.support.RequestContextUtils;

public class CustomLocaleChangeInterceptor extends LocaleChangeInterceptor {

	private final List<String> supportedLocales = Arrays.asList("en", "rs");

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws ServletException {
		String newLocale = request.getParameter(this.getParamName());
		if (newLocale != null && supportedLocales.contains(newLocale)) {
			LocaleResolver resolver = RequestContextUtils.getLocaleResolver(request);
			if (resolver == null) {
				throw new IllegalStateException("No LocaleResolver found");
			}
			resolver.setLocale(request, response, StringUtils.parseLocaleString(newLocale));
		}
		return true;
	}

}
