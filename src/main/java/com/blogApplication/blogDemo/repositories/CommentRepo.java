package com.blogApplication.blogDemo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogApplication.blogDemo.entities.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer>{

}
