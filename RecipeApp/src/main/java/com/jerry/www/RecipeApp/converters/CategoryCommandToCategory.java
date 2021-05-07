package com.jerry.www.RecipeApp.converters;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import com.jerry.www.RecipeApp.commands.CategoryCommand;
import com.jerry.www.RecipeApp.model.Category;

@Component
public class CategoryCommandToCategory implements Converter<CategoryCommand, Category>{

	@Override
	public Category convert(CategoryCommand source) {
		if (source == null) {
			return null;
		}
		final Category category = new Category();
		category.setId(source.getId());
		category.setCategoryName(source.getCategoryName());
		//Scategory.setRecipes(new RecipeCommandToRecipe().convert(source.getRecipes()));
		return category;
	}

	@Override
	public JavaType getInputType(TypeFactory typeFactory) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JavaType getOutputType(TypeFactory typeFactory) {
		// TODO Auto-generated method stub
		return null;
	}

}
