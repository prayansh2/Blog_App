package com.prayansh.blog.serviceImpl;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.prayansh.blog.Repositiry.CategoryRepo;
import com.prayansh.blog.entities.Category;
import com.prayansh.blog.exception.ResourceNotFound;
import com.prayansh.blog.payload.CategoryDto;
import com.prayansh.blog.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{

	private CategoryRepo catrepo;

	private ModelMapper modelMapper;
	
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category cat=this.modelMapper.map(categoryDto, Category.class);
		Category addcat=this.catrepo.save(cat);
		return this.modelMapper.map(addcat,CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		
		Category cat=this.catrepo.findById(categoryId)
		.orElseThrow(()->new ResourceNotFound("category", "Category Id", categoryId));
		
		cat.setCategorytitle(categoryDto.getCategoryTitle());
		cat.setCategorydescription(categoryDto.getCategorydescription());
		Category save = this.catrepo.save(cat);
		return this.modelMapper.map(save, CategoryDto.class);
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		Category cat=this.catrepo.findById(categoryId)
	 .orElseThrow(()->new ResourceNotFound("category", "Category Id", categoryId));
			this.catrepo.delete(cat);
	}

	@Override
	public CategoryDto getCategory(Integer categoryId) {
		Category cat=this.catrepo.findById(categoryId)
		.orElseThrow(()->new ResourceNotFound("category", "Category Id", categoryId));
				
		return this.modelMapper.map(cat, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getCategories() {
		List<Category> findAll = this.catrepo.findAll();
		List<CategoryDto> collect = findAll.stream().map(e->this.modelMapper.
				map(e, CategoryDto.class)).collect(Collectors.toList());
		return collect;
	}

}
