package com.jerry.www.RecipeApp.converters;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.jerry.www.RecipeApp.commands.CategoryCommand;
import com.jerry.www.RecipeApp.model.Category;

class CategoryToCategoryCommandTest {

	public static final Long LONG_VALUE = 1L;
	public static final String CATEGORY = "category";
	
	CategoryToCategoryCommand converter;

	@BeforeEach
	void setUp() throws Exception {
		converter = new CategoryToCategoryCommand();
	}

	@Test
	public void nullParameterTest() throws Exception {
		assertNull(converter.convert(null));
	}
	
	@Test
	public void emptyObjectTest() throws Exception {
		assertNotNull(converter.convert(new Category()));
	}
	
	@Test
	public void converterTest() throws Exception {
		//given 
		Category category = new Category();
		category.setId(LONG_VALUE);
		category.setCategoryName(CATEGORY);
		//command.setRecipes(null);
		
		//when 
		CategoryCommand command = converter.convert(category);
		
		//then 
		assertNotNull(command);
		assertEquals(LONG_VALUE, command.getId());
		assertEquals(CATEGORY, command.getCategoryName());
		
	}

}
