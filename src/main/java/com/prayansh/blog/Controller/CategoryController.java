package com.prayansh.blog.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.prayansh.blog.payload.ApiResponse;
import com.prayansh.blog.payload.CategoryDto;
import com.prayansh.blog.serviceImpl.CategoryServiceImpl;

@RestController("/api/categories")
public class CategoryController {

	private CategoryServiceImpl catserv;
	
	//create
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto catdto)
	{
	CategoryDto createCategory = this.catserv.createCategory(catdto);
	return new ResponseEntity<CategoryDto>(createCategory,HttpStatus.CREATED);
	}
	
	
	//update
	@PutMapping("/{catid}")
	public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto catdto,@PathVariable Integer catid)
	{
		CategoryDto updateCategory = this.catserv.updateCategory(catdto, catid);
		return new ResponseEntity<CategoryDto>(updateCategory,HttpStatus.OK);
	}
	
	//delete
	@DeleteMapping("/{catid}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer catid)
	{
		this.catserv.deleteCategory(catid);
		return new ResponseEntity<ApiResponse>(new ApiResponse("category is deleted successfully",true),HttpStatus.OK);
	}
	
	
	//get
	@GetMapping("/{catid}")
	public ResponseEntity<CategoryDto> getCategory(@PathVariable Integer catid)
	{
		CategoryDto category = this.catserv.getCategory(catid);
		return new ResponseEntity<CategoryDto>(category,HttpStatus.OK);
	}
	
	
	//getAll
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getAllCategory()
	{
		List<CategoryDto> categories = this.catserv.getCategories();
		return new ResponseEntity<List<CategoryDto>>(categories,HttpStatus.OK);
	}
	
}
