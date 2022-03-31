package com.bootstrap.dao.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		if (request.getLocale().getLanguage().equals("rs")) {
			String url = request.getContextPath() + "/admin/login?logout&lang=rs";
			response.sendRedirect(url);
		}

		response.sendRedirect("/admin/login?logout");
	}

}
