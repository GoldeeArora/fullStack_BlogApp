package com.blogApplication.blogDemo.payloads;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

import com.blogApplication.blogDemo.entities.Category;
import com.blogApplication.blogDemo.entities.User;
import com.blogApplication.blogDemo.entities.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class PostDto {
	private Integer postId;
private String title;
private String content;
private MultipartFile image;
private Date addedDate;


private CategoryDto category;


private UserDto user;

private Set<CommentDto> comments = new HashSet<>(); 
}
