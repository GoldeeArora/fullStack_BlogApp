package com.blogApplication.blogDemo.services;

import com.blogApplication.blogDemo.payloads.CommentDto;

public interface CommentService {
	CommentDto createComment(CommentDto commentDto,Integer postId);
	
    void deleteComment(Integer commentId);
}
