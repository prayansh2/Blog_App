package com.prayansh.blog.Repositiry;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prayansh.blog.entities.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer>{

}
