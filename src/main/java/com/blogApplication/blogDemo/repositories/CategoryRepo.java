package com.blogApplication.blogDemo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogApplication.blogDemo.entities.Category;

public interface CategoryRepo extends JpaRepository<Category,Integer>{

}
