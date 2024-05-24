package com.prayansh.blog.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.prayansh.blog.Repositiry.CategoryRepo;
import com.prayansh.blog.Repositiry.PostRepo;
import com.prayansh.blog.Repositiry.UserRepo;
import com.prayansh.blog.entities.Category;
import com.prayansh.blog.entities.Post;
import com.prayansh.blog.entities.User;
import com.prayansh.blog.exception.ResourceNotFound;
import com.prayansh.blog.payload.PostDto;
import com.prayansh.blog.payload.PostResponse;
import com.prayansh.blog.service.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepo postrepo;
	@Autowired
	private UserRepo urepo;
	@Autowired
	private CategoryRepo crepo;
	@Autowired
	private ModelMapper model;

	@Override
	public PostDto createPost(PostDto postdto, Integer uid, Integer cid) {
		// fetch user
		User user = this.urepo.findById(uid).orElseThrow(() -> new ResourceNotFound("User", "User id:", uid));
		// fetch category
		Category cat = this.crepo.findById(cid).orElseThrow(() -> new ResourceNotFound("category", "category id", cid));

		// create post
		Post post = this.model.map(postdto, Post.class);
		post.setAddDate(new Date());
		post.setImageName("default.png");
		post.setCategory(cat);
		post.setUser(user);
		Post save = this.postrepo.save(post);
		return this.model.map(save, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postdto, int id) {
		Post post = this.postrepo.findById(id).
		orElseThrow(()->new ResourceNotFound("Post", "Post id", id));
		post.setTitle(postdto.getTitle());
		post.setContent(postdto.getContent());
		post.setImageName(postdto.getImageName());
		Post save = this.postrepo.save(post);
		
		return this.model.map(save,PostDto.class);
	}

	@Override
	public void deletePost(int id) {
		Post p = this.postrepo.findById(id).
		orElseThrow(()->new ResourceNotFound("Post", "Post id", id));
		
		this.postrepo.delete(p);
	}

	@Override
	public PostDto getPost(Integer id) {
		Post p = this.postrepo.findById(id).
		orElseThrow(()->new ResourceNotFound("Post", "Post id", id));
				
		return this.model.map(p, PostDto.class);
	}

	@Override
	public PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy) {

		//normal 
//		List<Post> posts = this.postrepo.findAll();
//		return posts.stream().map(e->this.model.map(e, PostDto.class)).collect(Collectors.toList());
	
	//pagable
		
//		int pageSize=5;
//		int pageNumber=1;
		Pageable p=PageRequest.of(pageNumber, pageSize,Sort.by(sortBy).descending());
		Page<Post> pagePost = this.postrepo.findAll(p);
		List<Post> posts = pagePost.getContent();
		 List<PostDto> collect = posts.stream().map(e->this.model.map(e, PostDto.class)).collect(Collectors.toList());
		PostResponse postres=new  PostResponse();
		postres.setContent(collect);
		postres.setPageNumber(pagePost.getNumber());
		postres.setPageSize(pagePost.getSize());
		postres.setTotalElements(pagePost.getNumberOfElements());
		postres.setTotalPages(pagePost.getTotalPages());
		postres.setLastPage(pagePost.isLast());
		return postres;
	}

	@Override
	public List<PostDto> getAllByCategory(Integer catid) {
		// fetch category
		Category cat = this.crepo.findById(catid)
	.orElseThrow(() -> new ResourceNotFound("category", "category id", catid));
		List<Post> posts = this.postrepo.findAllByCategory(cat);
		return posts.stream().map(e->
			this.model.map(e, PostDto.class)).collect(Collectors.toList());
	}

	@Override
	public List<PostDto> getAllByUser(Integer userid) {
		// fetch user
	User user = this.urepo.findById(userid).
	orElseThrow(() -> new ResourceNotFound("User", "User id:", userid));
	List<Post> posts = this.postrepo.findAllByUser(user);
		return posts.stream().map(e->
		this.model.map(e, PostDto.class)).collect(Collectors.toList());
	}

	@Override
	public List<PostDto> searchPost(String keyword) {
		List<Post> posts = this.postrepo.findByTitleContaining("%"+keyword+"%");
		return posts.stream().map(e->this.model.map(e, PostDto.class)).collect(Collectors.toList());
	}

}
