package com.blogApplication.blogDemo.services;

import java.util.List;


import com.blogApplication.blogDemo.payloads.PostDto;
import com.blogApplication.blogDemo.payloads.PostDto2;
import com.blogApplication.blogDemo.payloads.PostResponse;

public interface PostService {

//create
PostDto2 createPost(PostDto postDto,Integer userId,Integer categoryId);	
//update
PostDto2 updatePost(PostDto postDto,Integer postId);
	
//delete

	void deletePost(Integer postId);

//getById
	PostDto2 getById(Integer postId);
	
//getByCategory
	List<PostDto2> getByCategory(Integer categoryId);
	
	
//getByUsers
	List<PostDto2> getByUser(Integer userId);
	
	
	 
//getAll
	PostResponse getAllPosts(Integer pageNumber,Integer pageSize,String sortBy);
	
//search Posts
	List<PostDto2> searchPost(String keyword);

}
