package com.jerry.www.RecipeApp.converters;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
	void nullObjectTest() throws Exception {
	}
	
	@Test
	void emptyObjectTest() throws Exception {
		fail("Not yet implemented");
	}
	
	@Test
	void converterTest() throws Exception {
		fail("Not yet implemented");
	}

	@Test
	void converterWithNullUOMTest() throws Exception {
		fail("Not yet implemented");
	}
}
