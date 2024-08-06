package com.nt.filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.nt.helper.JWTHelper;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {
	@Autowired
	private UserDetailsService service;
	@Autowired
	private JWTHelper jwt;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		System.out.println("JwtFilter.doFilterInternal()");
		String header = request.getHeader("Authorization");
		String token = null;
		String userName = null;
		if (header != null && header.startsWith("Bearer ")) {

			token = header.substring(7);
			userName = this.jwt.getUserName(token);
		}

		if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {

			UserDetails users = service.loadUserByUsername(userName);
			boolean isValid = jwt.validateToken(token, users.getUsername());

			if (isValid) {

				System.out.println(users);

				UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(users.getUsername(),
						users.getPassword(), users.getAuthorities());
				auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(auth);
			}

		}
		filterChain.doFilter(request, response);

	}

}