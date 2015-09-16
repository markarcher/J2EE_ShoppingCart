package com.example.dao;

import java.util.List;

import com.example.domain.Category;


public interface CategoryDao {

	void saveCategory(Category category);

	List<Category> getAllCategories();

	Category getCategoryId(String categoryName);



	

}
