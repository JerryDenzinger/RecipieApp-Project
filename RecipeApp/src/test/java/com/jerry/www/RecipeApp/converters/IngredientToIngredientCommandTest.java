package com.jerry.www.RecipeApp.converters;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.jerry.www.RecipeApp.commands.IngredientCommand;
import com.jerry.www.RecipeApp.model.Ingredient;
import com.jerry.www.RecipeApp.model.Recipe;

class IngredientToIngredientCommandTest {
	public static final Recipe RECIPE = new Recipe();
	public static final BigDecimal AMOUNT = new BigDecimal("1");
	public static final String DESCRIPTION = "Cheesburger";
	public static final Long ID_VALUE = 1L;
	public static final Long UOM_ID = 2L;

	IngredientToIngredientCommand converter;

	@BeforeEach
	void setUp() throws Exception {
		converter = new IngredientToIngredientCommand();
	}

	@Test
	void nullParameterTest() throws Exception {
		assertNull(converter.convert(null));
	}
	
	@Test
	void emptyObjectTest() throws Exception {
		assertNotNull(converter.convert(new Ingredient()));
	}
	
	@Test
	void converterTest() throws Exception {
		//given 
		Ingredient ingredient = new Ingredient();
		ingredient.setId(ID_VALUE);
		ingredient.setDescription(DESCRIPTION);
		ingredient.setAmount(AMOUNT);
		ingredient.setRecipe(RECIPE);
		//ingredient.setUom();
		
		//when 
		IngredientCommand command = converter.convert(ingredient);
		
		//then 
		assertNotNull(command);
		assertEquals(ID_VALUE, command.getId());
		assertEquals(DESCRIPTION, command.getDescription());
		assertEquals(RECIPE,command.getRecipe());
		assertEquals(AMOUNT, command.getAmount());
		//assertEquals(UOM_ID, ingredient.getUom().getId());
		
	}

	@Test
	void converterWithNullUOMTest() throws Exception {
		fail("Not yet implemented");
	}
}
