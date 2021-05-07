package com.jerry.www.RecipeApp.converters;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.jerry.www.RecipeApp.commands.CategoryCommand;
import com.jerry.www.RecipeApp.commands.IngredientCommand;
import com.jerry.www.RecipeApp.commands.NotesCommand;
import com.jerry.www.RecipeApp.commands.RecipeCommand;
import com.jerry.www.RecipeApp.model.Difficulty;
import com.jerry.www.RecipeApp.model.Recipe;


class RecipeCommandToRecipeTest {
	public static final Long RECIPE_ID = 1L;
	public static final String DESCRIPTION = "description";
	public static final Integer PREP_TIME = 10;
	public static final Integer COOK_TIME = 10;
	public static final Integer SERVINGS = 10;
	public static final String SOURCE = "Source";
	public static final String URL = "Url";
	public static final String DIRECTIONS = "Directions";
	public static final Difficulty DIFFICULTY = Difficulty.EASY;
	public static final Long CAT_ID_1 = 1L;
	public static final Long CAT_ID_2 = 2L;
	public static final Long INGRED_ID_1 = 3L;
	public static final Long INGRED_ID_2 = 4L;
	public static final Long NOTES_ID = 9L;

	
	RecipeCommandToRecipe converter;

	@BeforeEach
	public void setUp() throws Exception {
		converter = new RecipeCommandToRecipe(new CategoryCommandToCategory(),
                new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure()),
                new NotesCommandToNotes());
	}

	@Test
	public void nullParameterTest() throws Exception {
		assertNull(converter.convert(null));
	}

	@Test
	public void emptyObjectTest() throws Exception {
		assertNotNull(converter.convert(new RecipeCommand()));
	}

	@Test
	public void converterTest() throws Exception {
		// given
		RecipeCommand command = new RecipeCommand();
		command.setId(RECIPE_ID);
		command.setDescription(DESCRIPTION);
		command.setPrepTime(PREP_TIME);
		command.setCookTime(COOK_TIME);
		command.setServings(SERVINGS);
		command.setSource(SOURCE);
		command.setUrl(URL);
		command.setDirections(DIRECTIONS);
		command.setDifficulty(DIFFICULTY);
		
		NotesCommand notesCom = new NotesCommand();
		notesCom.setId(NOTES_ID);
		
		command.setNotes(notesCom);
		
		CategoryCommand categoryCom = new CategoryCommand();
		categoryCom.setId(CAT_ID_1);
		CategoryCommand categoryCom2 = new CategoryCommand();
		categoryCom2.setId(CAT_ID_2);
		
		command.getCategories().add(categoryCom);
		command.getCategories().add(categoryCom2);
		
		IngredientCommand ingredientCom = new IngredientCommand();
		ingredientCom.setId(INGRED_ID_1);
		IngredientCommand ingredientCom2 = new IngredientCommand();
		ingredientCom2.setId(INGRED_ID_2);
		
		command.getIngredients().add(ingredientCom);
		command.getIngredients().add(ingredientCom2);


		// when
		Recipe recipe = converter.convert(command);

		// then
		assertNotNull(recipe);
		assertEquals(RECIPE_ID, recipe.getId());
		assertEquals(DESCRIPTION, recipe.getDescription());
		assertEquals(PREP_TIME, recipe.getPrepTime());
		assertEquals(COOK_TIME, recipe.getCookTime());
		assertEquals(SERVINGS, recipe.getServings());
		assertEquals(SOURCE, recipe.getSource());
		assertEquals(URL, recipe.getUrl());
		assertEquals(DIRECTIONS, recipe.getDirections());
		assertEquals(DIFFICULTY, recipe.getDifficulty());
		assertEquals(DESCRIPTION, recipe.getDescription());
		assertEquals(NOTES_ID, recipe.getNotes().getId());
        assertEquals(2, recipe.getCategories().size());
        assertEquals(2, recipe.getIngredients().size());

	}

}
