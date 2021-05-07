package com.jerry.www.RecipeApp.converters;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.jerry.www.RecipeApp.commands.IngredientCommand;
import com.jerry.www.RecipeApp.model.Ingredient;
import com.jerry.www.RecipeApp.model.Recipe;

class IngredientCommandToIngredientTest {
	public static final Recipe RECIPE = new Recipe();
	public static final BigDecimal AMOUNT = new BigDecimal("1");
	public static final String DESCRIPTION = "Cheesburger";
	public static final Long ID_VALUE = 1L;
	public static final Long UOM_ID = 2L;

	IngredientCommandToIngredient converter;

	@BeforeEach
	void setUp() throws Exception {
		converter = new IngredientCommandToIngredient();
	}

	@Test
	void nullParameterTest() throws Exception {
		assertNull(converter.convert(null));
	}
	
	@Test
	void emptyObjectTest() throws Exception {
		assertNotNull(converter.convert(new IngredientCommand()));
	}
	
	@Test
	void converterTest() throws Exception {
		//given 
		IngredientCommand command = new IngredientCommand();
		command.setId(ID_VALUE);
		command.setDescription(DESCRIPTION);
		command.setAmount(AMOUNT);
		command.setRecipe(RECIPE);
		//command.setUom(null);
		
		//when 
		Ingredient ingredient = converter.convert(command);
		
		//then 
		assertNotNull(ingredient);
		assertEquals(ID_VALUE, ingredient.getId());
		assertEquals(DESCRIPTION, ingredient.getDescription());
		assertEquals(RECIPE,ingredient.getRecipe());
		assertEquals(AMOUNT, ingredient.getAmount());
		//assertEquals(UOM_ID, ingredient.getUom().getId());
		
	}

	@Test
	void converterWithNullUOMTest() throws Exception {
		fail("Not yet implemented");
	}
}
