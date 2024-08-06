package com.nt.cfgs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.ProviderManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;

import com.nt.service.IUserService;

@Configuration
public class MyConfiguration {
	
	@Autowired
	private UserDetailsService service;
	
	@Autowired
	private PasswordEncoder encoder;

	/*
	 * @Bean public UserDetailsManager setUserDetails() { UserDetails build =
	 * User.builder() .username("prem") .password(getPassword().encode("1234"))
	 * .roles("admin","clerk") .build(); return new
	 * InMemoryUserDetailsManager(build); }
	 */
	@Bean
	public DaoAuthenticationConfigurer<?, UserDetailsService> setUserDetails() {
		
				          
		return new DaoAuthenticationConfigurer<>(service).passwordEncoder(encoder);
	}
	
	
	@Bean
	public AuthenticationManager getAuthenticationManager(AuthenticationConfiguration builder) throws Exception {
		return builder.getAuthenticationManager();
	}
}
