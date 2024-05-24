package com.prayansh.blog;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Basic1Application {

	public static void main(String[] args) {
		SpringApplication.run(Basic1Application.class, args);
	}
	
	@Bean
	public ModelMapper mpdelMapper()
	{
		return new ModelMapper();
	}

}
