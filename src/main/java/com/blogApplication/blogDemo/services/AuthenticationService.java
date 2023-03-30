package com.blogApplication.blogDemo.services;

import com.blogApplication.blogDemo.payloads.AuthenticationResponse;
import com.blogApplication.blogDemo.payloads.LoginDto;
import com.blogApplication.blogDemo.payloads.UserDto;

public interface AuthenticationService {
  AuthenticationResponse register(UserDto userDto);
  

AuthenticationResponse authenticate(LoginDto request);
}
