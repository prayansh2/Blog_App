package com.prayansh.blog.payload;



import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Setter
public class UserDto {

	private int id;
	private String name;
	private String email;
	private String password;
	private String about;
}

