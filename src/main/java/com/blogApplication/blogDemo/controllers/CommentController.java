package com.blogApplication.blogDemo.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogApplication.blogDemo.payloads.ApiResponse;
import com.blogApplication.blogDemo.payloads.CommentDto;
import com.blogApplication.blogDemo.services.CommentService;



@RestController
@RequestMapping("/api")
public class CommentController {
	@Autowired
	private CommentService commentService;
	@PostMapping("/post/{postId}/comments")
public ResponseEntity<CommentDto> creatComment(@RequestBody CommentDto comment,@PathVariable Integer postId)
{
	CommentDto createdCommentDto = this.commentService.createComment(comment, postId);
	return new ResponseEntity<CommentDto>(createdCommentDto,HttpStatus.ACCEPTED);
}
	@DeleteMapping("/comments/{commentId}")
	public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId)
	{
		this.commentService.deleteComment(commentId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Comment has been deleted",true),HttpStatus.OK);
	}
}
