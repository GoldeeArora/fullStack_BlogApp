package com.blogApplication.blogDemo.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.blogApplication.blogDemo.exceptions.*;
import com.blogApplication.blogDemo.entities.User;
import com.blogApplication.blogDemo.payloads.UserDto;
import com.blogApplication.blogDemo.repositories.UserRepo;
import com.blogApplication.blogDemo.services.UserService;
@Service
public class UserServiceImpl implements UserService {
	@Autowired
 private UserRepo userRepo;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Override
	public UserDto createUser(UserDto userDto) {
	User user = this.dtoToUser(userDto);
	user.setPassword(passwordEncoder.encode(userDto.getPassword()) );
	User savedUser = this.userRepo.save(user);
	
		return this.userToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		User user = this.userRepo.findById(userId).orElseThrow((()->new ResourceNotFoundException("User", "id", userId)));
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		user.setAbout(userDto.getAbout());
		User updatedUser = this.userRepo.save(user);
		return this.userToDto(updatedUser);
	}

	@Override
	public UserDto getUserById(Integer userId) {
		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User"," Id ",userId));
		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllusers() {
		List<User> users = this.userRepo.findAll();
		List<UserDto> userDtos = users.stream().map(user-> this.userToDto(user)).collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public void deleteUser(Integer userId) {
	User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "Id", userId));
	this.userRepo.delete(user);

	}
	public User dtoToUser(UserDto userDto)
	{
	User user = this.modelMapper.map(userDto, User.class);
//	User user = new User();
//	user.setId(userDto.getId());
//	user.setName(userDto.getName());
//	user.setEmail(userDto.getEmail());
//	user.setPassword(userDto.getPassword());
//	user.setAbout(userDto.getAbout());
	return user;
		
	}
	public UserDto userToDto(User user)
	{
		UserDto userDto = this.modelMapper.map(user, UserDto.class);
//		UserDto userDto = new UserDto();
//		userDto.setId(user.getId());
//		userDto.setName(user.getName());
//		userDto.setEmail(user.getEmail());
//		userDto.setPassword(user.getPassword());
//		userDto.setAbout(user.getAbout());
		return userDto;
	}

}
