package com.jerry.www.RecipeApp.service;

import static org.junit.jupiter.api.Assertions.*;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.jerry.www.RecipeApp.commands.RecipeCommand;
import com.jerry.www.RecipeApp.converters.RecipeCommandToRecipe;
import com.jerry.www.RecipeApp.converters.RecipeToRecipeCommand;
import com.jerry.www.RecipeApp.model.Recipe;
import com.jerry.www.RecipeApp.repositories.RecipeRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
class RecipeServiceIT {

	public static final String NEW_DESCRIPTION = "New Description";

	@Autowired
	RecipeService recipeService;

	@Autowired
	RecipeRepository recipeRepository;

	@Autowired
	RecipeCommandToRecipe recipeCommandToRecipe;

	@Autowired
	RecipeToRecipeCommand recipeToRecipeCommand;

	@Transactional
	@Test
	public void testSaveOfDescription() throws Exception {
		// given
		Iterable<Recipe> recipes = recipeRepository.findAll();
		Recipe testRecipe = recipes.iterator().next();
		RecipeCommand testRecipeCommand = recipeToRecipeCommand.convert(testRecipe);

		// when
		testRecipeCommand.setDescription(NEW_DESCRIPTION);
		RecipeCommand savedRecipeCommand = recipeService.saveRecipeCommand(testRecipeCommand);

		// then
		assertEquals(NEW_DESCRIPTION, savedRecipeCommand.getDescription());
		assertEquals(testRecipe.getId(), savedRecipeCommand.getId());
		assertEquals(testRecipe.getCategories().size(), savedRecipeCommand.getCategories().size());
		assertEquals(testRecipe.getIngredients().size(), savedRecipeCommand.getIngredients().size());
	}
}