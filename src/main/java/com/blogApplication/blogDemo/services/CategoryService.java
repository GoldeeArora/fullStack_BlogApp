package com.blogApplication.blogDemo.services;

import java.util.List;

import com.blogApplication.blogDemo.payloads.CategoryDto;

//This interface is used to loose coupling. so that we can change it's implementation in future
public interface CategoryService {
//	create
	CategoryDto createCategory(CategoryDto categoryDto);
//	update
 CategoryDto updateCategory(CategoryDto categoryDto,Integer categoryId);
// delete
 void deleteCategory(Integer categoryId);
// get
 CategoryDto getCategory(Integer categoryId);
// getAll
 List<CategoryDto> getCategories();
 
 
}
