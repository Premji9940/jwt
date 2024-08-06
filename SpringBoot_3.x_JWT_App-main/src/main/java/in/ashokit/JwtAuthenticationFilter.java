package in.ashokit;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
//This filter is used to validate or autheticate the user credentials 
//This class is executed for each request if request is having Authorize header,then validating with jwt helper class
//then creat authentication inside security context
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private JwtHelper jwtHelper;

	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

//        try {
//            Thread.sleep(500);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
		// Authorization 

		String requestHeader = request.getHeader("Authorization");
		// Bearer 2352345235sdfrsfgsdfsdf
		String username = null;
		String token = null;
		if (requestHeader != null && requestHeader.startsWith("Bearer")) {
			// looking good
			token = requestHeader.substring(7); 
			try {

				username = this.jwtHelper.getUsernameFromToken(token);

			} catch (IllegalArgumentException e) {
				logger.info("Illegal Argument while fetching the username !!");
				e.printStackTrace();
			} catch (ExpiredJwtException e) {
				logger.info("Given jwt token is expired !!");
				e.printStackTrace();
			} catch (MalformedJwtException e) {
				logger.info("Some changed has done in token !! Invalid Token");
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();

			}

		} else {
		}

		//
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

			// fetch user detail from username
			UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
			System.out.println(userDetails.getUsername());
			Boolean validateToken = this.jwtHelper.validateToken(token, userDetails);
			System.out.println(validateToken);
			if (validateToken) {

				// set the authentication
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				//final object stored in security context with user details(User name and password)
				SecurityContextHolder.getContext().setAuthentication(authentication);

			} else { 
				logger.info("Validation fails !!");
			}

		}

		filterChain.doFilter(request, response);

	}
}