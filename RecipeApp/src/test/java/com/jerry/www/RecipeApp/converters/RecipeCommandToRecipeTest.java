package com.jerry.www.RecipeApp.converters;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.jerry.www.RecipeApp.commands.RecipeCommand;
import com.jerry.www.RecipeApp.model.Difficulty;
import com.jerry.www.RecipeApp.model.Recipe;


class RecipeCommandToRecipeTest {
	public static final Long LONG_VALUE = 1L;
	public static final String DESCRIPTION = "description";
	public static final Integer PREP_TIME = 10;
	public static final Integer COOK_TIME = 10;
	public static final Integer SERVINGS = 10;
	public static final String SOURCE = "Source";
	public static final String URL = "Url";
	public static final String DIRECTIONS = "Directions";
	//public static final Set<IngredientCommand> INGREDIENTS = new Recipe().getIngredients();
	//public static final Byte[] IMAGE = new Byte[];
	public static final Difficulty DIFFICULTY = new Recipe().getDifficulty();
	//public static final NotesCommand NOTES = new Recipe().getNotes();
	//public static final Set<CategoryCommand> CATEGORY = new Recipe().getCategories() ;
	
	RecipeCommandToRecipe converter;

	@BeforeEach
	public void setUp() throws Exception {
		converter = new RecipeCommandToRecipe();
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
		command.setId(LONG_VALUE);
		command.setDescription(DESCRIPTION);
		command.setPrepTime(PREP_TIME);
		command.setCookTime(COOK_TIME);
		command.setServings(SERVINGS);
		command.setSource(SOURCE);
		command.setUrl(URL);
		command.setDirections(DIRECTIONS);
		//command.setIngredients(INGREDIENTS);
		//command.setImage(null);
		command.setDifficulty(DIFFICULTY);
		//command.setNotes(NOTES);
		//command.setCategories(null);

		// when
		Recipe recipe = converter.convert(command);

		// then
		assertNotNull(recipe);
		assertEquals(LONG_VALUE, recipe.getId());
		assertEquals(DESCRIPTION, recipe.getDescription());
		assertEquals(PREP_TIME, recipe.getPrepTime());
		assertEquals(COOK_TIME, recipe.getCookTime());
		assertEquals(SERVINGS, recipe.getServings());
		assertEquals(SOURCE, recipe.getSource());
		assertEquals(URL, recipe.getUrl());
		assertEquals(DIRECTIONS, recipe.getDescription());
		assertEquals(DIFFICULTY, recipe.getDescription());
		assertEquals(DESCRIPTION, recipe.getDescription());
		

	}

}
