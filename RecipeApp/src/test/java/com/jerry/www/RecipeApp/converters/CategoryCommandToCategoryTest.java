package com.jerry.www.RecipeApp.converters;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.jerry.www.RecipeApp.commands.CategoryCommand;
import com.jerry.www.RecipeApp.model.Category;

class CategoryCommandToCategoryTest {
	public static final Long LONG_VALUE = 1L;
	public static final String CATEGORY = "category";
	
	CategoryCommandToCategory converter;

	@BeforeEach
	void setUp() throws Exception {
		converter = new CategoryCommandToCategory();
	}

	@Test
	public void nullParameterTest() throws Exception {
		assertNull(converter.convert(null));
	}
	
	@Test
	public void emptyObjectTest() throws Exception {
		assertNotNull(converter.convert(new CategoryCommand()));
	}
	
	@Test
	public void converterTest() throws Exception {
		//given 
		CategoryCommand command = new CategoryCommand();
		command.setId(LONG_VALUE);
		command.setCategoryName(CATEGORY);
		//command.setRecipes(null);
		
		//when 
		Category category = converter.convert(command);
		
		//then 
		assertNotNull(category);
		assertEquals(LONG_VALUE, category.getId());
		assertEquals(CATEGORY, category.getCategoryName());
		
	}
}
