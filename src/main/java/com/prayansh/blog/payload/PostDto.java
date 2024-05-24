package com.prayansh.blog.payload;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {
	
	private Integer id;

	private String title;
	 
	private String imageName;
	
	private String content;
	
	private Date addDate;
	
	private UserDto user;
	
	private CategoryDto category;
	
	private Set<CommentDto> comments=new HashSet<>();

}
