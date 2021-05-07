package com.jerry.www.RecipeApp.converters;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import com.jerry.www.RecipeApp.commands.RecipeCommand;
import com.jerry.www.RecipeApp.model.Recipe;
import com.sun.istack.Nullable;

import lombok.Synchronized;

@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {

	@Synchronized
	@Nullable
	@Override
	public Recipe convert(RecipeCommand source) {
		if (source == null) {
			return null;
		}
		final Recipe recipe = new Recipe();
		recipe.setId(source.getId());
		recipe.setDescription(source.getDescription());
		recipe.setPrepTime(source.getPrepTime());
		recipe.setCookTime(source.getCookTime());
		recipe.setServings(source.getServings());
		recipe.setSource(source.getSource());
		recipe.setUrl(source.getUrl());
		recipe.setDirections(source.getDirections());
		//recipe.setIngredients(new IngredientCommandToIngredient().convert(source.getIngredients());
		recipe.setImage(source.getImage());
		recipe.setDifficulty(source.getDifficulty());
		recipe.setNotes(new NotesCommandToNotes().convert(source.getNotes()));
		return recipe;
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
