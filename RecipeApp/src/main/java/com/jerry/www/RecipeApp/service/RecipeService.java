package com.jerry.www.RecipeApp.service;

import java.util.Set;

import com.jerry.www.RecipeApp.commands.RecipeCommand;
import com.jerry.www.RecipeApp.model.Recipe;

public interface RecipeService {

	Set<Recipe> getRecipes();

	Recipe findById(Long id);

	RecipeCommand saveRecipeCommand(RecipeCommand command);

	RecipeCommand findCommandById(Long id);

	void deleteById(Long idToDelete);
	
	

}
