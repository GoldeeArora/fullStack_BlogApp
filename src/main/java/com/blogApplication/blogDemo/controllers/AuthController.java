package com.blogApplication.blogDemo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogApplication.blogDemo.payloads.AuthenticationResponse;
import com.blogApplication.blogDemo.payloads.LoginDto;
import com.blogApplication.blogDemo.payloads.UserDto;
import com.blogApplication.blogDemo.services.AuthenticationService;



@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	 private AuthenticationService authenticationService;
		@PostMapping("/register")
		public ResponseEntity<AuthenticationResponse> register(
				@RequestBody UserDto request
				)
		{
			return ResponseEntity.ok(authenticationService.register(request));
		}
		@PostMapping("/authenticate")
		public ResponseEntity<AuthenticationResponse>  authenticate(
				@RequestBody LoginDto request
				)
		{
			return ResponseEntity.ok(authenticationService.authenticate(request));
		}

}
