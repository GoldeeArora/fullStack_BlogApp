package com.blogApplication.blogDemo.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.ManyToAny;

import com.blogApplication.blogDemo.payloads.CommentDto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name= "posts")
@Getter
@Setter
@NoArgsConstructor
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer postId;
private String title;
private String content;
private String imageName;
private Date addedDate;

@ManyToOne
@JoinColumn(name="category_id")
private Category category;

@ManyToOne
private User user;

@OneToMany(mappedBy = "post",cascade = CascadeType.ALL)
private Set<Comment> comments = new HashSet<>();
public void setAddedDate(Date date)
{
	this.addedDate = date;
	}
}
