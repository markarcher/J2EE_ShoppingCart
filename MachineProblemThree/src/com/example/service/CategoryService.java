package com.example.service;

import java.util.List;

import com.example.domain.Category;

public interface CategoryService {

	void saveCategory(Category category);

	List<Category> getAllCategories();

	Category getCategoryNo(String categoryName);

}
