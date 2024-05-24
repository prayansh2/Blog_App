package com.prayansh.blog.service;

import java.util.List;

import com.prayansh.blog.payload.UserDto;



public interface UserService {
	public UserDto createUser(UserDto u);
	public UserDto updateUser(UserDto dto,int id);
	public void deleteUser(int userid);
	public UserDto getUserById(int id);
	public List<UserDto> getAllUsers();
}
