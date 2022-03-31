package com.bootstrap.dao.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.bootstrap.dao.repositories.jpa.UserRepository;
import com.bootstrap.dao.services.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Lazy
	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private UserRepository userRepo;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(encoder()).and()
				.authenticationProvider(daoAuthenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/edit/**").hasAuthority("ADMIN")
				.antMatchers("/chromosomes/**/locus/**", "/**/dna-extraction", "/**/G-banding",
						"/**/fluorescent-in-situ-hybridization", "/**/polymerase-chain-reaction")
				.hasAnyAuthority("ADMIN", "USER").anyRequest().permitAll().and().formLogin().loginPage("/admin/login")
				.permitAll().and().logout().invalidateHttpSession(true).clearAuthentication(true)
				.deleteCookies("JSESSIONID").permitAll().and().exceptionHandling().accessDeniedPage("/accessDenied");
		http.sessionManagement().maximumSessions(1).expiredUrl("/login?expired");

	}

	@Bean
	public PasswordEncoder encoder() {
		Map<String, PasswordEncoder> encoders = new HashMap<>();
		encoders.put("bcrypt", new BCryptPasswordEncoder());
		DelegatingPasswordEncoder dpe = new DelegatingPasswordEncoder("bcrypt", encoders);
		dpe.setDefaultPasswordEncoderForMatches(new BCryptPasswordEncoder());
		return dpe;
	}

	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(new UserDetailsServiceImpl(userRepo));
		daoAuthenticationProvider.setPasswordEncoder(encoder());
		return daoAuthenticationProvider;
	}
}
