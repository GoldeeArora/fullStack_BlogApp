package com.blogApplication.blogDemo.controllers;

import java.util.List;

import org.aspectj.weaver.NewConstructorTypeMunger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableLoadTimeWeaving;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blogApplication.blogDemo.config.AppConstants;
import com.blogApplication.blogDemo.payloads.ApiResponse;
import com.blogApplication.blogDemo.payloads.PostDto;
import com.blogApplication.blogDemo.payloads.PostDto2;
import com.blogApplication.blogDemo.payloads.PostResponse;
import com.blogApplication.blogDemo.services.PostService;

@RestController
@RequestMapping("/api/")
public class PostController {
	@Autowired
	private PostService postService;
	//create post
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto2> createPost(@ModelAttribute PostDto postDto ,@PathVariable Integer userId,@PathVariable Integer categoryId)
	{
		PostDto2 createdPostDto = this.postService.createPost(postDto, userId, categoryId);
		return new ResponseEntity<PostDto2>(createdPostDto,HttpStatus.CREATED);
		
	}
	//get post by user
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto2>> getPostsByUser(@PathVariable Integer userId)
	{
		List<PostDto2> postDtos = this.postService.getByUser(userId);
		return new ResponseEntity<List<PostDto2>>(postDtos,HttpStatus.OK);
	}
	//get post by category
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto2>> getPostsByCategory(@PathVariable Integer categoryId)
	{
		List<PostDto2> postDtos = this.postService.getByCategory(categoryId);
		return new ResponseEntity<List<PostDto2>>(postDtos,HttpStatus.OK);
	}
	//get all posts
	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getAllPosts(
			@RequestParam(value = "pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required = false) Integer pageNumber,
			@RequestParam(value = "pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false) Integer pageSize,
			@RequestParam(value="sortBy",defaultValue = AppConstants.SORT_BY,required = false) String sortBy
			)
	{
		PostResponse postResponse= this.postService.getAllPosts(pageNumber,pageSize,sortBy);
		return new ResponseEntity<PostResponse>(postResponse,HttpStatus.OK);
	}
	//get posts by postId
	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDto2> getPostById(@PathVariable Integer postId)
	{
		PostDto2 postDto = this.postService.getById(postId);
		return new ResponseEntity<PostDto2>(postDto,HttpStatus.OK);
	}
	
	//Delete Post
	@DeleteMapping("/posts/{postId}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId)
	{
		this.postService.deletePost(postId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Post has been deleted",true),HttpStatus.OK);
	}
	
	//Update post
	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostDto2> updatePost(@RequestBody PostDto postDto,@PathVariable Integer postId)
	{
		PostDto2 updatedDto = this.postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto2>(updatedDto,HttpStatus.OK);
		
	}
	
	//search Title
	@GetMapping("/posts/search/{keyword}")
	public ResponseEntity<List<PostDto2>> searchKeyword(@PathVariable String keyword)
	{
		List<PostDto2> postDtos = this.postService.searchPost(keyword);
		return new ResponseEntity<List<PostDto2>>(postDtos,HttpStatus.OK);
	}


}
