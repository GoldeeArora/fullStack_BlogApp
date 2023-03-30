package com.blogApplication.blogDemo.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogApplication.blogDemo.entities.Category;
import com.blogApplication.blogDemo.exceptions.ResourceNotFoundException;
import com.blogApplication.blogDemo.payloads.CategoryDto;
import com.blogApplication.blogDemo.repositories.CategoryRepo;
import com.blogApplication.blogDemo.services.CategoryService;
@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
 private CategoryRepo categoryRepo;
	@Autowired
	private ModelMapper modelMapper;
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category cat = this.modelMapper.map(categoryDto, Category.class);
		Category addedCat = this.categoryRepo.save(cat);
		return this.modelMapper.map(addedCat,CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
	Category category = this.categoryRepo.findById(categoryId).orElseThrow((()->new ResourceNotFoundException("Category", "id", categoryId)));;
  category.setCategoryDescription(categoryDto.getCategoryDescription());
  category.setCategoryTitle(categoryDto.getCategorytitle());
 Category updatedCategory =  this.categoryRepo.save(category);
		return this.modelMapper.map(updatedCategory, CategoryDto.class);
	}

	@Override
	public void deleteCategory(Integer categoryId) {
	Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","Id",categoryId));
	this.categoryRepo.delete(cat);
	

	}

	@Override
	public CategoryDto getCategory(Integer categoryId) {
	Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "Id", categoryId));
             return this.modelMapper.map(cat, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getCategories() {
		List<Category> listCat = this.categoryRepo.findAll();
		List<CategoryDto> catDtos = listCat.stream().map(cat-> this.modelMapper.map(cat,CategoryDto.class)).collect(Collectors.toList());
		return catDtos;
	}

}
