package com.blogApplication.blogDemo.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;



import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter{
	@Autowired
	private final JwtService jwtService;
	 @Autowired
	 private final UserDetailsService userDetailsService;
		@Override
		protected void doFilterInternal(
				HttpServletRequest request, 
				HttpServletResponse response, 
				FilterChain filterChain
				)
				throws ServletException, IOException {
			// TODO Auto-generated method stub
//			this will check does the request contains the header And stores it
			final String authHeader = request.getHeader("Authorization");
			 final String jwt;
			 final String userEmail;
			if(authHeader == null || !authHeader.startsWith("Bearer "))
			{
//	         makes the Call to the next filter
				filterChain.doFilter(request, response);
				return;
			}
//			getting data after Bearer
			jwt = authHeader.substring(7);
			userEmail = jwtService.extractUsername(jwt);//todo extract the userEmail;
			if(userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null)
			{
				UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
				if(jwtService.isTokenValid(jwt, userDetails)) {
					UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities()); 
					authToken.setDetails(
							new WebAuthenticationDetailsSource().buildDetails(request)
							);
					SecurityContextHolder.getContext().setAuthentication(authToken);
				}
	// passing to the other filter from jwt filter
				filterChain.doFilter(request,response);
			}
		}
}
