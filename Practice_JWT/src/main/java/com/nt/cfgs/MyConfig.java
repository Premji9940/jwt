package com.nt.cfgs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.UserDetailsServiceConfigurer;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;

import com.nt.service.UserServiceImpl;

@Configuration
public class MyConfig {
	@Autowired private UserServiceImpl service;
	@Bean
	public DaoAuthenticationConfigurer<AuthenticationManagerBuilder, UserServiceImpl> config() throws Exception {
	/*	UserDetails u=User.builder().username("premji")
				.password(getEncoder().encode("9940"))
				.roles("Admin")
				.build();
				new InMemoryUserDetailsManager(u);*/
		
		return  new DaoAuthenticationConfigurer<AuthenticationManagerBuilder, UserServiceImpl>(service).passwordEncoder(getEncoder());
	}
	//creating Encoder
		@Bean
		public PasswordEncoder getEncoder() {
			return new BCryptPasswordEncoder();
		}
		// creating Authentication manger
		@Bean

		public AuthenticationManager getAuthenticationManager(AuthenticationConfiguration builder) throws Exception {
			return builder.getAuthenticationManager();
		}
}
