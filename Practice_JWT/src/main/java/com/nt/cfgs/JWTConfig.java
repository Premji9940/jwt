package com.nt.cfgs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import com.nt.filter.JwtFilter;

@Configuration
public class JWTConfig {

	@Autowired
	JWTAuthenticationEntrypoint entry;
	@Autowired
	JwtFilter filter;// =new JwtFilter();

	

	// creating security filter chain
	@Bean
	public SecurityFilterChain configuration(HttpSecurity http) throws Exception {
		return http.csrf(c -> c.disable())
				
				  .authorizeHttpRequests((r) -> {
				  r.requestMatchers("/login").permitAll().requestMatchers("/wish").hasAnyAuthority(
						  "manager", "admin").requestMatchers("/hi")
				  .hasAuthority("admin").anyRequest().authenticated(); })
				 
				// .authorizeHttpRequests((r)->{r.requestMatchers("/login").permitAll().requestMatchers("/wish").authenticated().anyRequest().authenticated();})

				.exceptionHandling((ex) -> ex.authenticationEntryPoint(entry))
				.sessionManagement((ses) -> ses.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class).build();
	}

}
