package com.prayansh.blog.Repositiry;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prayansh.blog.entities.User;

public interface UserRepo extends JpaRepository<User, Integer>{

	public Optional<User> findByName(String email);
	
}
