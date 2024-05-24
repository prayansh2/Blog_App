package com.prayansh.blog.serviceImpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prayansh.blog.Repositiry.CommentRepo;
import com.prayansh.blog.Repositiry.PostRepo;
import com.prayansh.blog.entities.Comment;
import com.prayansh.blog.entities.Post;
import com.prayansh.blog.exception.ResourceNotFound;
import com.prayansh.blog.payload.CommentDto;
import com.prayansh.blog.payload.PostResponse;
import com.prayansh.blog.service.CommentService;

@Service
public class CommentServImpl implements CommentService{

	@Autowired
	private PostRepo prepo;
	
	@Autowired
	private CommentRepo crepo;
	
	@Autowired
	private ModelMapper map;
	
	@Override
	public CommentDto createComment(CommentDto commentdto, Integer postid) {
		Post post=this.prepo.findById(postid).orElseThrow(()->new ResourceNotFound("post", "postid", postid));
		
		Comment comment = this.map.map(commentdto, Comment.class);
		
		comment.setPost(post);
		Comment save = this.crepo.save(comment);
		return this.map.map(save, CommentDto.class);
	}

	@Override
	public void deleteComment(Integer commentid) {
		Comment comment = this.crepo.findById(commentid).orElseThrow(()->new ResourceNotFound("comment","comment id", commentid));
			this.crepo.delete(comment);
			
	}

}
