package com.prayansh.blog.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prayansh.blog.payload.ApiResponse;
import com.prayansh.blog.payload.UserDto;
import com.prayansh.blog.serviceImpl.UserServiceImpl;


@RestController()
@RequestMapping("/api/user")
public class UserController {
	@Autowired
	private UserServiceImpl serv;

	@PostMapping("/")
	public ResponseEntity<UserDto> create(@RequestBody UserDto u) {
		UserDto d = this.serv.createUser(u);
		return ResponseEntity.status(HttpStatus.CREATED).body(d);
	}

	@GetMapping("/{uid}")
	public ResponseEntity<UserDto> get(@PathVariable int uid) {
		UserDto d = this.serv.getUserById(uid);
		System.out.println(d);
		return ResponseEntity.ok(d);
	}

	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAll() {
		List<UserDto> ds = this.serv.getAllUsers();
		return ResponseEntity.ok(ds);
	}

	@PutMapping("/{id}")
	public ResponseEntity<UserDto> update(@RequestBody UserDto u, @PathVariable int id) {
		UserDto d = this.serv.updateUser(u, id);
		return ResponseEntity.ok(d);
	}

	@DeleteMapping("/{uid}")
	public ResponseEntity<ApiResponse> delete(@PathVariable int uid) {
		this.serv.deleteUser(uid);
		return ResponseEntity.ok(new ApiResponse("Successfully deleted", true));
	}

}
