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
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand>{

	@Synchronized
	@Nullable
	@Override
	public RecipeCommand convert(Recipe source) {
		if (source == null) {
			return null;
		}
		final RecipeCommand recipeCom = new RecipeCommand();
		recipeCom.setId(source.getId());
		recipeCom.setDescription(source.getDescription());
		recipeCom.setPrepTime(source.getPrepTime());
		recipeCom.setCookTime(source.getCookTime());
		recipeCom.setServings(source.getServings());
		recipeCom.setSource(source.getSource());
		recipeCom.setUrl(source.getUrl());
		recipeCom.setDirections(source.getDirections());
		//recipeCom.setIngredients(new IngredientCommandToIngredient().convert(source.getIngredients());
		recipeCom.setImage(source.getImage());
		recipeCom.setDifficulty(source.getDifficulty());
		recipeCom.setNotes(new NotesToNotesCommand().convert(source.getNotes()));
		return recipeCom;
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
