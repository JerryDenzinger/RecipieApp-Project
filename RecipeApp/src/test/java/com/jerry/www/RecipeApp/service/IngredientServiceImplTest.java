package com.jerry.www.RecipeApp.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;

import com.jerry.www.RecipeApp.commands.IngredientCommand;
import com.jerry.www.RecipeApp.converters.IngredientCommandToIngredient;
import com.jerry.www.RecipeApp.converters.IngredientToIngredientCommand;
import com.jerry.www.RecipeApp.converters.UnitOfMeasureCommandToUnitOfMeasure;
import com.jerry.www.RecipeApp.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.jerry.www.RecipeApp.model.Ingredient;
import com.jerry.www.RecipeApp.model.Recipe;
import com.jerry.www.RecipeApp.repositories.RecipeRepository;
import com.jerry.www.RecipeApp.repositories.UnitOfMeasureRepository;

import jdk.internal.org.jline.utils.Log;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class IngredientServiceImplTest {

	private final IngredientToIngredientCommand ingredientToIngredientCommand;
	private final IngredientCommandToIngredient ingredientCommandToIngredient;

	@Mock
	RecipeRepository recipeRepository;

	@Mock
	UnitOfMeasureRepository unitOfMeasureRepository;
	
	RecipeService recipeService;

	IngredientService ingredientService;

	public IngredientServiceImplTest() {
		this.ingredientToIngredientCommand = new IngredientToIngredientCommand(
				new UnitOfMeasureToUnitOfMeasureCommand());
		this.ingredientCommandToIngredient = new IngredientCommandToIngredient(
				new UnitOfMeasureCommandToUnitOfMeasure());
	}

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		ingredientService = new IngredientServiceImpl(ingredientToIngredientCommand, ingredientCommandToIngredient,
				recipeRepository, unitOfMeasureRepository);

	}

	@Test
	void findByRecipeIdAndIngredientIdTest() throws Exception {

	}

	@Test
	public void findByRecipeIdAndHappyPath() throws Exception {
		// given
		Recipe recipe = new Recipe();
		recipe.setId((1L));

		Ingredient ingredient1 = new Ingredient();
		ingredient1.setId(1L);

		Ingredient ingredient2 = new Ingredient();
		ingredient2.setId(2L);

		Ingredient ingredient3 = new Ingredient();
		ingredient3.setId(3L);

		recipe.addIngredient(ingredient1);
		recipe.addIngredient(ingredient2);
		recipe.addIngredient(ingredient3);

		Optional<Recipe> recipeOptional = Optional.of(recipe);

		// when
		when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

		// then
		IngredientCommand ingredientCommand = ingredientService.findByRecipeIdAndIngredientId(1L, 3L);

		assertEquals(Long.valueOf(3L), ingredientCommand.getId());
		assertEquals(Long.valueOf(1L), ingredientCommand.getRecipeId());
		verify(recipeRepository).findById(anyLong());

	}

	@Test
	public void saveRecipeCommandTest() throws Exception {
		// given
		IngredientCommand command = new IngredientCommand();
		command.setId(3L);
		command.setRecipeId(2L);

		Optional<Recipe> recipeOptional = Optional.of(new Recipe());

		Recipe savedRecipe = new Recipe();
		savedRecipe.addIngredient(new Ingredient());
		savedRecipe.getIngredients().iterator().next().setId(3L);

		when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);
		when(recipeRepository.save(any())).thenReturn(savedRecipe);

		// when
		IngredientCommand savedCommand = ingredientService.saveIngredientCommand(command);

		// then
		assertEquals(Long.valueOf(3L), savedCommand.getId());
		verify(recipeRepository).findById(anyLong());
		verify(recipeRepository).save(any(Recipe.class));

	}

	@Test
	public void deleteInredientFromRecipeTest() throws Exception {
		//given 
		Recipe recipe = new Recipe();
		Ingredient ingredient = new Ingredient();
		recipe.setId(1L);
		ingredient.setId(3L);
		recipe.addIngredient(ingredient);
		ingredient.setRecipe(recipe);
		recipeRepository.save(recipe);
		log.debug("recipe from test = " + recipe.toString() + "ingredients " + recipe.getIngredients().isEmpty());
		
		Optional<Recipe> recipeOptional = Optional.of(new Recipe());

		//when 
		when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);
		
		ingredientService.deleteById(1L, 3L);
		
		//Then 
		verify(recipeRepository).findById(anyLong());
		verify(recipeRepository).save(any(Recipe.class));
	}

}
