package com.prayansh.blog.service;

import java.util.List;

import com.prayansh.blog.payload.CategoryDto;

import jakarta.persistence.criteria.CriteriaBuilder.In;

public interface CategoryService {
	
	//create
	
	public CategoryDto createCategory(CategoryDto categoryDto);
	
	//update
	
	CategoryDto updateCategory(CategoryDto categoryDto,Integer categoryId);
	
	//delete
	
	public void deleteCategory(Integer categoryId);
	
	//get
	CategoryDto getCategory(Integer categoryId);
	
	//getAll
	
	List<CategoryDto> getCategories();
}
