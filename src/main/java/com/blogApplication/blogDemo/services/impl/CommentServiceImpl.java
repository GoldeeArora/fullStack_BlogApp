package com.blogApplication.blogDemo.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogApplication.blogDemo.entities.Comment;
import com.blogApplication.blogDemo.entities.Post;
import com.blogApplication.blogDemo.exceptions.ResourceNotFoundException;
import com.blogApplication.blogDemo.payloads.CommentDto;
import com.blogApplication.blogDemo.repositories.CommentRepo;
import com.blogApplication.blogDemo.repositories.PostRepo;
import com.blogApplication.blogDemo.services.CommentService;
@Service
public class CommentServiceImpl implements CommentService {
 @Autowired
	private CommentRepo commentRepo;
 @Autowired
 private PostRepo postRepo;
 @Autowired
 private ModelMapper modelMapper;
	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {
	Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("post", "id", postId));
	Comment comment = this.modelMapper.map(commentDto, Comment.class);
	comment.setPost(post);
	Comment savedComment = this.commentRepo.save(comment);
	
		return this.modelMapper.map(savedComment, CommentDto.class);
	}

	@Override
	public void deleteComment(Integer commentId) {
Comment comment = this.commentRepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment", "id", commentId));

this.commentRepo.delete(comment);

	}

}
