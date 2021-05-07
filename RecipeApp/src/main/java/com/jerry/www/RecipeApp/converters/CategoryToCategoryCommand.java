package com.jerry.www.RecipeApp.converters;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import com.jerry.www.RecipeApp.commands.CategoryCommand;
import com.jerry.www.RecipeApp.model.Category;

@Component
public class CategoryToCategoryCommand implements Converter<Category, CategoryCommand> {

	@Override
	public CategoryCommand convert(Category source) {
		if (source == null) {
			return null;
		}
		final CategoryCommand categoryCom = new CategoryCommand();
		categoryCom.setId(source.getId());
		categoryCom.setCategoryName(source.getCategoryName());
		//categoryCom.setRecipes(new RecipeToRecipeCommand().convert(source.getRecipes()));
		return categoryCom;
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
