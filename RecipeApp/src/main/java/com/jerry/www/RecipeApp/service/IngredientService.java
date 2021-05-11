package com.jerry.www.RecipeApp.service;

import com.jerry.www.RecipeApp.commands.IngredientCommand;

public interface IngredientService {

 IngredientCommand findByRecipeIdAndIngredientId(Long recipeId , Long ingredientId);

}
