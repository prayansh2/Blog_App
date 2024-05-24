package com.prayansh.blog.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFound extends RuntimeException{
	
	private String message;
	private String FieldName;
	private int id;
	
	public ResourceNotFound(String message,String name,int id){
		super(name+message+" because id not match " +id);
		this.FieldName=name;
		this.message=message;
		this.id=id;
	}
}

