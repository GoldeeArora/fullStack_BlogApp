package com.blogApplication.blogDemo.services.impl;

//import java.awt.print.Pageable;
import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

//import org.hibernate.metamodel.model.domain.internal.AbstractSqmPathSource;
import org.modelmapper.ModelMapper;
import org.modelmapper.internal.bytebuddy.asm.Advice.This;
//import org.modelmapper.internal.bytebuddy.asm.Advice.OffsetMapping.Sort;
//import org.modelmapper.internal.bytebuddy.asm.Advice.This;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.blogApplication.blogDemo.entities.Category;
import com.blogApplication.blogDemo.entities.Post;
import com.blogApplication.blogDemo.entities.User;
import com.blogApplication.blogDemo.exceptions.ResourceNotFoundException;
import com.blogApplication.blogDemo.payloads.PostDto;
import com.blogApplication.blogDemo.payloads.PostDto2;
import com.blogApplication.blogDemo.payloads.PostResponse;
import com.blogApplication.blogDemo.repositories.CategoryRepo;
import com.blogApplication.blogDemo.repositories.PostRepo;
import com.blogApplication.blogDemo.repositories.UserRepo;
import com.blogApplication.blogDemo.services.PostService;

//import ch.qos.logback.core.rolling.helper.IntegerTokenConverter;
@Service
public class PostServiceImpl implements PostService {
	@Autowired
  private PostRepo postRepo;
	@Autowired
	private CategoryRepo categoryRepo;
	@Autowired
	private UserRepo userRepo;
	@Autowired
   private ImageService imageService;
	@Autowired
	private ModelMapper modelMapper;
	@Override
	public PostDto2 createPost(PostDto postDto,Integer userId,Integer categoryId) {
	Post post = this.modelMapper.map(postDto, Post.class);
	User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User ", "Id", userId));
	Category category = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "Id", categoryId));
	String getImageLink;
	getImageLink = this.imageService.uploadFileToS3Bucket(postDto.getImage());
	
	post.setImageName(getImageLink);
	post.setAddedDate(new Date());
	post.setUser(user);
	post.setCategory(category);
	Post addedPost = this.postRepo.save(post);
	return this.modelMapper.map(addedPost, PostDto2.class);
	}

	@Override
	public PostDto2 updatePost(PostDto postDto, Integer postId) {
	Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("post", "Id", postId));
	String getImageLink;
	getImageLink = this.imageService.uploadFileToS3Bucket(postDto.getImage());
	post.setTitle(postDto.getTitle());
	post.setContent(postDto.getContent());
	post.setImageName(getImageLink);
	
	 this.postRepo.save(post);
		return this.modelMapper.map(post, PostDto2.class);
	}

	@Override
	public void deletePost(Integer postId) {
	Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","postId",postId));
   this.postRepo.delete(post);
	}

	@Override
	public PostDto2 getById(Integer postId) {
		Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "Id", postId));
		PostDto2 postDto = this.modelMapper.map(post, PostDto2.class);
		return postDto;
		
	}

	@Override
	public List<PostDto2> getByCategory(Integer categoryId) {
		Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "Id", categoryId));
		List<Post> posts = this.postRepo.findByCategory(cat);
		
	List<PostDto2> postDtos = posts.stream().map((post)-> this.modelMapper.map(post,PostDto2.class)).collect(Collectors.toList());
		
		
		
		return postDtos;
	}

	@Override
	public List<PostDto2> getByUser(Integer userId) {
		User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "Id", userId));
		List<Post> posts = this.postRepo.findByUser(user);
		
	List<PostDto2> postDtos = posts.stream().map((post)-> this.modelMapper.map(post,PostDto2.class)).collect(Collectors.toList());
		
		
		
		return postDtos;
	}

	@Override
	public  PostResponse getAllPosts(Integer pageNumber,Integer pageSize,String sortBy) {
		//implementing pagination
		PageRequest p = PageRequest.of(pageNumber, pageSize,Sort.by(sortBy));
		Page<Post> pagePost = this.postRepo.findAll(p);
List<Post> posts = pagePost.getContent();
		List<PostDto2> postDtos = posts.stream().map((post)-> this.modelMapper.map(post,PostDto2.class)).collect(Collectors.toList());
      PostResponse postResponse = new PostResponse();
      postResponse.setContent(postDtos);
      postResponse.setPageNumber(pagePost.getNumber());
      postResponse.setPageSize(pagePost.getSize());
      postResponse.setTotalElements(pagePost.getTotalElements());
      postResponse.setTotalPages(pagePost.getTotalPages());
      postResponse.setLastPage(pagePost.isLast());
      return postResponse;
	}

	@Override
	public List<PostDto2> searchPost(String keyword) {
		List<Post> posts = this.postRepo.findByTitleContaining(keyword);
		List<PostDto2> postDtos =  posts.stream().map((post)->this.modelMapper.map(post,PostDto2.class)).collect(Collectors.toList());
		
		return postDtos;
	}

}
