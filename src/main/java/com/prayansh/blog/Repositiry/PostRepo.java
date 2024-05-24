package com.prayansh.blog.Repositiry;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.prayansh.blog.entities.Category;
import com.prayansh.blog.entities.Post;
import com.prayansh.blog.entities.User;

public interface PostRepo extends JpaRepository<Post, Integer>{

	List<Post> findAllByUser(User user);
	
	List<Post> findAllByCategory(Category category);
	
	@Query("select p from post p where p title like :key")
	List<Post> findByTitleContaining(@Param("key")String title);
	
}
