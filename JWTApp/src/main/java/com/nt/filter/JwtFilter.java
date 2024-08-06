package com.nt.filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.header.Header;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.nt.exception.TokenExpiredException;
import com.nt.helper.JwtHelper;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {
	@Autowired
	private JwtHelper helper;
	@Autowired
	private UserDetailsService service;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
	/*	// Getting Token Value from request
		String requestHeader = request.getHeader("Authorization");
		System.out.println(requestHeader);
		String token = null;
		String userName = null;
		if (requestHeader != null && requestHeader.startsWith("Bearer")) {
			// we get token like Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwcm
			// To remove Bearer from token and return only token
			token = requestHeader.substring(7);

			// To get Username From Token
			userName = helper.getUsernameFromToken(token);

		}

		if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			// fetch user detail from username
			UserDetails userDetails = service.loadUserByUsername(userName);
			Boolean validateToken = this.helper.validateToken(token, userDetails);
			if (validateToken) {

				// set the authentication
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				// final object stored in security context with user details(User name and
				// password)
				SecurityContextHolder.getContext().setAuthentication(authentication);

			}

		}
		filterChain.doFilter(request, response);*/
		String requestHeader = request.getHeader("Authorization");
	
		// Bearer 2352345235sdfrsfgsdfsdf
		String username = null;
		String token = null;
		if (requestHeader != null && requestHeader.startsWith("Bearer")) {
			// looking good
			token = requestHeader.substring(7); 
			System.out.println(token);
			try {

				username = this.helper.getUserName(token);
				System.out.println("from filter"+username);
				

			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (ExpiredJwtException e) {
				e.printStackTrace();
			} catch (MalformedJwtException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();

			}
			try {
			boolean tokenExpire = helper.isTokenExpire(token);
			}catch(ExpiredJwtException e) {
			throw new TokenExpiredException("token expired");
			}
			

		}

		//
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

			// fetch user detail from username
			UserDetails userDetails = this.service.loadUserByUsername(username);
			System.out.println("From Token"+userDetails.getUsername());
			Boolean validateToken = this.helper.validateToken(token, userDetails.getUsername());
			System.out.println("isVAlidated"+validateToken);
			
			if (validateToken) {
				

				// set the authentication
				UsernamePasswordAuthenticationToken authentication = 
						new UsernamePasswordAuthenticationToken(
						userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				//final object stored in security context with user details(User name and password)
				SecurityContextHolder.getContext().setAuthentication(authentication);

			} 
		}

		filterChain.doFilter(request, response);


	}
	

}
