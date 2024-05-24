package com.prayansh.blog.payload;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Service
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse {
	
	String message;
	boolean success;

}
