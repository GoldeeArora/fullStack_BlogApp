package com.blogApplication.blogDemo.controllers;

import java.util.List;
import java.util.Map;

import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogApplication.blogDemo.payloads.UserDto;
import com.blogApplication.blogDemo.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {
	@Autowired
	private UserService userService;
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUsers()
	{
		
		return new ResponseEntity<List<UserDto>>(this.userService.getAllusers(),HttpStatus.OK); 
	}

	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getUser(@PathVariable("userId") Integer userId)
	{
		return new ResponseEntity<UserDto>(this.userService.getUserById(userId),HttpStatus.OK);
	}
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto)
	{
		UserDto createUserDto = this.userService.createUser(userDto);
		return new ResponseEntity<>(createUserDto,HttpStatus.CREATED);
	}
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,@PathVariable Integer userId)
	{
		UserDto updateUserDto = this.userService.updateUser(userDto, userId);
	return new ResponseEntity<UserDto>(updateUserDto,HttpStatus.OK);	
	}
	@DeleteMapping("/{userId}")
	public ResponseEntity<?> deleteUser(@PathVariable("userId") Integer userId)
	{
		this.userService.deleteUser(userId);
return new ResponseEntity<Object>(Map.of("message","User Deleted Successfully"),HttpStatus.OK);
	}


}
