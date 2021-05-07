package com.jerry.www.RecipeApp.converters;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.jerry.www.RecipeApp.commands.RecipeCommand;
import com.jerry.www.RecipeApp.model.Category;
import com.jerry.www.RecipeApp.model.Difficulty;
import com.jerry.www.RecipeApp.model.Ingredient;
import com.jerry.www.RecipeApp.model.Notes;
import com.jerry.www.RecipeApp.model.Recipe;

class RecipeToRecipeCommandTest {
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

	
	RecipeToRecipeCommand converter;

	@BeforeEach
	public void setUp() throws Exception {
		converter = new RecipeToRecipeCommand(new CategoryToCategoryCommand(),
				new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand()),
				new NotesToNotesCommand());
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
		recipe.setId(RECIPE_ID);
		recipe.setDescription(DESCRIPTION);
		recipe.setPrepTime(PREP_TIME);
		recipe.setCookTime(COOK_TIME);
		recipe.setServings(SERVINGS);
		recipe.setSource(SOURCE);
		recipe.setUrl(URL);
		recipe.setDirections(DIRECTIONS);
		recipe.setDifficulty(DIFFICULTY);
		
		Notes notes = new Notes();
		notes.setId(NOTES_ID);
		
		recipe.setNotes(notes);
		
		Category category = new Category();
		category.setId(CAT_ID_1);
		Category category2 = new Category();
		category2.setId(CAT_ID_2);
		
		recipe.getCategories().add(category);
		recipe.getCategories().add(category2);
		
		Ingredient ingredient = new Ingredient();
		ingredient.setId(INGRED_ID_1);
		Ingredient ingredient2 = new Ingredient();
		ingredient2.setId(INGRED_ID_2);
		
		recipe.getIngredients().add(ingredient);
		recipe.getIngredients().add(ingredient2);


		// when
		RecipeCommand command = converter.convert(recipe);

		// then
		assertNotNull(command);
		assertEquals(RECIPE_ID, command.getId());
		assertEquals(DESCRIPTION, command.getDescription());
		assertEquals(PREP_TIME, command.getPrepTime());
		assertEquals(COOK_TIME, command.getCookTime());
		assertEquals(SERVINGS, command.getServings());
		assertEquals(SOURCE, command.getSource());
		assertEquals(URL, command.getUrl());
		assertEquals(DIRECTIONS, command.getDirections());
		assertEquals(DIFFICULTY, command.getDifficulty());
		assertEquals(DESCRIPTION, command.getDescription());
		assertEquals(NOTES_ID, command.getNotes().getId());
        assertEquals(2, command.getCategories().size());
        assertEquals(2, command.getIngredients().size());
		

	}

}
