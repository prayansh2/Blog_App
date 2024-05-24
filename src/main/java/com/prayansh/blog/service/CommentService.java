package com.prayansh.blog.service;

import com.prayansh.blog.payload.CommentDto;

public interface CommentService {
	
	CommentDto createComment(CommentDto commentdto,Integer postid);
	
	void deleteComment(Integer commentid);
}
