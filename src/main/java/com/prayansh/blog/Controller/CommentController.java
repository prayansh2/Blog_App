package com.prayansh.blog.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prayansh.blog.payload.ApiResponse;
import com.prayansh.blog.payload.CommentDto;
import com.prayansh.blog.serviceImpl.CommentServImpl;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
	
	@Autowired
	private CommentServImpl cserv;
	
	@PostMapping("/post/{postid}/comments")
	public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentdto,@PathVariable Integer postid)
	{
		CommentDto comment = this.cserv.createComment(commentdto, postid);
		return new ResponseEntity<CommentDto>(comment,HttpStatus.OK);
	}
	
	@DeleteMapping("/comments/{commentId}")
	public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId)
	{
		this.cserv.deleteComment(commentId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("comment is deleted!!",true),HttpStatus.OK);
	}
	
}
