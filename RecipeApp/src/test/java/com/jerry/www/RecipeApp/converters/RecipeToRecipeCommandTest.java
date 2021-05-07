package com.jerry.www.RecipeApp.converters;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.jerry.www.RecipeApp.commands.RecipeCommand;
import com.jerry.www.RecipeApp.model.Difficulty;
import com.jerry.www.RecipeApp.model.Recipe;

class RecipeToRecipeCommandTest {

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
	
	RecipeToRecipeCommand converter;

	@BeforeEach
	public void setUp() throws Exception {
		converter = new RecipeToRecipeCommand();
	}

	@Test
	public void nullParameterTest() throws Exception {
		assertNull(converter.convert(null));
	}

	@Test
	public void emptyObjectTest() throws Exception {
		assertNotNull(converter.convert(new Recipe()));
	}

	@Test
	public void converterTest() throws Exception {
		// given
		Recipe recipe = new Recipe();
		recipe.setId(LONG_VALUE);
		recipe.setDescription(DESCRIPTION);
		recipe.setPrepTime(PREP_TIME);
		recipe.setCookTime(COOK_TIME);
		recipe.setServings(SERVINGS);
		recipe.setSource(SOURCE);
		recipe.setUrl(URL);
		recipe.setDirections(DIRECTIONS);
		//command.setIngredients(INGREDIENTS);
		//command.setImage(null);
		recipe.setDifficulty(DIFFICULTY);
		//command.setNotes(NOTES);
		//command.setCategories(null);

		// when
		RecipeCommand command = converter.convert(recipe);

		// then
		assertNotNull(command);
		assertEquals(LONG_VALUE, command.getId());
		assertEquals(DESCRIPTION, command.getDescription());
		assertEquals(PREP_TIME, command.getPrepTime());
		assertEquals(COOK_TIME, command.getCookTime());
		assertEquals(SERVINGS, command.getServings());
		assertEquals(SOURCE, command.getSource());
		assertEquals(URL, command.getUrl());
		assertEquals(DIRECTIONS, command.getDescription());
		assertEquals(DIFFICULTY, command.getDescription());
		assertEquals(DESCRIPTION, command.getDescription());
		

	}

}
