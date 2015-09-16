package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.dao.CategoryDao;
import com.example.domain.Category;

@Repository("categorydao")
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryDao categorydao;
	
	
	@Transactional(readOnly = false)
	@Override
	public void saveCategory(Category category) {
		categorydao.saveCategory(category);
		
	}

	@Transactional(readOnly = false)
	@Override
	public List<Category> getAllCategories() {
		List<Category> categories = categorydao.getAllCategories();
		return categories;
	}
	
	@Transactional(readOnly = false)
	@Override
	public Category getCategoryNo(String categoryName) {
		
		Category category = categorydao.getCategoryId(categoryName);
		return category;
	}
	
	
	
	
	
}
