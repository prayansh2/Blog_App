package com.prayansh.blog.Repositiry;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prayansh.blog.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer>{

}
