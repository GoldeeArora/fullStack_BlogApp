package com.blogApplication.blogDemo.services.impl;

import java.lang.invoke.CallSite;
import java.lang.reflect.Method;

import org.hibernate.loader.ast.spi.Loadable;
import org.hibernate.sql.results.graph.entity.internal.BatchEntityInsideEmbeddableSelectFetchInitializer;
import org.modelmapper.ModelMapper;
import org.modelmapper.internal.bytebuddy.asm.Advice.This;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configurers.ldap.LdapAuthenticationProviderConfigurer.PasswordCompareConfigurer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.blogApplication.blogDemo.config.JwtService;
import com.blogApplication.blogDemo.entities.User;
import com.blogApplication.blogDemo.payloads.AuthenticationResponse;
import com.blogApplication.blogDemo.payloads.LoginDto;
import com.blogApplication.blogDemo.payloads.UserDto;
import com.blogApplication.blogDemo.repositories.UserRepo;
import com.blogApplication.blogDemo.services.AuthenticationService;

import jakarta.websocket.Decoder.Text;
import lombok.experimental.var;
@Service
public class AuthenticationServiceImpl implements AuthenticationService {
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private JwtService jwtService;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private AuthenticationManager authenticationManager;

	@Override
	public AuthenticationResponse register(UserDto request ) {
		
		User user = this.modelMapper.map(request, User.class);
		user.setPassword(passwordEncoder.encode(request.getPassword()));
				userRepo.save(user);
				var jwtToken = jwtService.generateToken(user);
				return new AuthenticationResponse(jwtToken,request);
	}

	@Override
	public AuthenticationResponse authenticate(LoginDto request) {
//		Internally it Calls userDetailsService Method  to load the user details from the database based on the username(email) 
//		It uses PasswordEncoder interface which provices matches() method to  compare both the plain-Text and the encoded password present inside the database
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
				);
		var user = userRepo.findByEmail(request.getEmail()).orElseThrow();
		UserDto userDto = this.modelMapper.map(user, UserDto.class);
		var jwtToken = jwtService.generateToken(user);
		return new AuthenticationResponse(jwtToken,userDto);
	}

}
