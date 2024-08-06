package com.nt.cfgs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.nt.filter.JwtFilter;
import com.nt.helper.JwtAuthenticationEntryPoint;

@Configuration
public class SecurityConfig {

	@Autowired
	private JwtAuthenticationEntryPoint point;

	@Autowired
	private JwtFilter filter;
	@Bean
	public PasswordEncoder getPassword() {
		return new BCryptPasswordEncoder();
	}
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		/*
		 * http.csrf(csrf -> csrf.disable()) .authorizeHttpRequests()
		 * 
		 * .requestMatchers("/wish").authenticated().requestMatchers("/log").permitAll()
		 * .anyRequest() .authenticated() .and().exceptionHandling(ex ->
		 * ex.authenticationEntryPoint(point)) .sessionManagement(session ->
		 * session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)); //register
		 * filter for second request on wards http.addFilterBefore(filter,
		 * UsernamePasswordAuthenticationFilter.class); return http.build();
		 */
		http.csrf(csrf -> csrf.disable()).authorizeHttpRequests((p) -> {
			p.requestMatchers("/wish").authenticated().requestMatchers("/log","/save").permitAll().anyRequest()

					.authenticated();

		})
		.exceptionHandling(ex -> ex.authenticationEntryPoint(point))
		.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		// register filter for second request on wards
		http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}

}
