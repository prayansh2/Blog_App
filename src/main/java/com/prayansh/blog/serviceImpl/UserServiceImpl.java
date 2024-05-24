package com.prayansh.blog.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prayansh.blog.Repositiry.UserRepo;
import com.prayansh.blog.entities.User;
import com.prayansh.blog.exception.ResourceNotFound;
import com.prayansh.blog.payload.UserDto;
import com.prayansh.blog.service.UserService;



@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo repo;
	
	@Autowired
	private ModelMapper map;
	@Override
	public UserDto createUser(UserDto u) {
		User user=this.map.map(u, User.class);
		User save = this.repo.save(user);
		UserDto d=this.map.map(save,UserDto.class);
		return d;
	}

	@Override
	public UserDto updateUser(UserDto dto,int id) {
		User u = this.repo.findById(id)
			.orElseThrow(()->new ResourceNotFound("Uncessfull updation", "User", id));
		User u1 = this.map.map(dto, User.class);
		u1.setId(id);
		User save = this.repo.save(u1);
		return this.map.map(save, UserDto.class);
	}

	@Override
	public void deleteUser(int userid) {
		User u = this.repo.findById(userid)
		  .orElseThrow(()->new ResourceNotFound("Uncessfull deletion", "User", userid));
		this.repo.delete(u);
	}

	@Override
	public UserDto getUserById(int id) {
		User u=this.repo.findById(id).orElseThrow(()->new ResourceNotFound
				("Not Present", "User", id));
		UserDto u1 = this.map.map(u, UserDto.class);
		return u1;
	}

	@Override
	public List<UserDto> getAllUsers() {
	
		List<User> users = this.repo.findAll();
	List<UserDto> collect = users.stream()
			.map(u->this.map.map(u, UserDto.class)).collect(Collectors.toList());
		return collect;
	}
	
	

}
