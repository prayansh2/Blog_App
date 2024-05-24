package com.prayansh.blog.Controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.prayansh.blog.payload.ApiResponse;
import com.prayansh.blog.payload.PostDto;
import com.prayansh.blog.payload.PostResponse;
import com.prayansh.blog.serviceImpl.FileServiceImpl;
import com.prayansh.blog.serviceImpl.PostServiceImpl;

import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;

@RestController("/api/")
public class PostController {

	@Autowired
	private PostServiceImpl serv;
	@Autowired
	private FileServiceImpl fserv;
	
	@Value("${project.images}")
	private String path;
	//create
	@PostMapping("/user/{userId}/category/{catId}/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postdto
											,@PathVariable Integer userId,
											@PathVariable Integer catId)
	{
		PostDto createPost = this.serv.createPost(postdto, userId, catId);
		return new ResponseEntity<PostDto>(createPost,HttpStatus.CREATED);
	}
	
	//update
	@PutMapping("/post/{postid}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto post,@PathVariable Integer postid)
	{
		PostDto updatePost = this.serv.updatePost(post, postid);
		return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);
	}
	
	//delete
	@DeleteMapping("/post/{postid}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postid)
	{
		this.serv.deletePost(postid);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Post deleted",true),HttpStatus.OK);
	}
	
	
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Integer userid)
	{
		List<PostDto> allByUser = this.serv.getAllByUser(userid);
		return new ResponseEntity<List<PostDto>>(allByUser,HttpStatus.OK);
	}
	
	@GetMapping("/category/{catId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable Integer catid)
	{
		List<PostDto> allByUser = this.serv.getAllByCategory(catid);
		return new ResponseEntity<List<PostDto>>(allByUser,HttpStatus.OK);
	}
	
	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getAllPosts(
	@RequestParam(value="pageNumber",defaultValue = "1",required = false) Integer pageNumber,
	@RequestParam(value="pageszie",defaultValue = "5",required = false) Integer pageSize,
	@RequestParam(value="sortBy",defaultValue ="postId",required = false)String sortBy)
	{
		PostResponse allPost = this.serv.getAllPost(pageNumber,pageNumber,sortBy);
		return new ResponseEntity<PostResponse>(allPost,HttpStatus.OK);
	}
	
	@GetMapping("/post/{postid}")
	public ResponseEntity<PostDto> getPost(@PathVariable Integer postid)
	{
		PostDto post = this.serv.getPost(postid);
		return new ResponseEntity<PostDto>(post,HttpStatus.OK);
	}
	
	//search
	@GetMapping("/posts/search/{keywords}")
	public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable("keywords")String keywords)
	{
		List<PostDto> searchPost = this.serv.searchPost(keywords);
		return new ResponseEntity<List<PostDto>>(searchPost,HttpStatus.OK);
	}
	
	//post upload
	@PostMapping("/post/image/upload/{postId}")
	public ResponseEntity<PostDto> uploadPostImage(
			@RequestParam("image") MultipartFile image,
			@PathVariable("postId") Integer id) throws IOException
	{
		PostDto post = this.serv.getPost(id);
		String filename = this.fserv.uploadImage(path, image);
		post.setImageName(filename);
		PostDto updatePost = this.serv.updatePost(post, id);
	return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);
	}
	
	@GetMapping(value="/post/image/{imageName}",produces= MediaType.IMAGE_JPEG_VALUE)
	public void downloadImage(
	@PathVariable("imageName") String imageName,
	HttpServletResponse response)throws IOException
	{
		InputStream resource = this.fserv.getSource(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource, response.getOutputStream());
	}
}
