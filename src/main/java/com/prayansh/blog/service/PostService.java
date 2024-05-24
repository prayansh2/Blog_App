package com.prayansh.blog.service;

import java.util.List;

import com.prayansh.blog.payload.PostDto;
import com.prayansh.blog.payload.PostResponse;

public interface PostService {

	
	//create
	
	PostDto createPost(PostDto postdto,Integer postid,Integer catid);
	
	//update
	
	PostDto updatePost(PostDto postdto,int id);
	
	//delete
	
	void deletePost(int id);
	
	//get by id
	
	PostDto getPost(Integer id);
	
	//getAll post
	
	PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy);
	
	//get by user
	
	List<PostDto> getAllByCategory(Integer catid);
	
	//get by category
	
	List<PostDto> getAllByUser(Integer userid);
	
	//search
	
	List<PostDto> searchPost(String keyword);

}
