package com.blogApplication.blogDemo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogApplication.blogDemo.entities.Category;
import com.blogApplication.blogDemo.entities.Post;
import com.blogApplication.blogDemo.entities.User;

public interface PostRepo extends JpaRepository<Post, Integer> {
	 List<Post> findByUser(User user);
	 List<Post> findByCategory(Category category);
     List<Post> findByTitleContaining(String title);

}
