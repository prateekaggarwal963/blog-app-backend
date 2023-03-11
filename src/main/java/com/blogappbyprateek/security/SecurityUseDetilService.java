package com.blogappbyprateek.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityUseDetilService {


	@Bean
	public UserDetailsService userDetailsService() {
		//return new CustomUserDetailService();
		InMemoryUserDetailsManager detailsManager = new InMemoryUserDetailsManager();
		UserDetails user = User.withUsername("prateek").password("prateek").passwordEncoder(s->sspasswordEncoder().encode(s)).roles("ADMIN").build();
		detailsManager.createUser(user);
		return detailsManager;
	}
	
	@Bean
	public BCryptPasswordEncoder sspasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
