package com.jerry.www.RecipeApp.controllers;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.jerry.www.RecipeApp.commands.IngredientCommand;
import com.jerry.www.RecipeApp.commands.RecipeCommand;
import com.jerry.www.RecipeApp.service.IngredientService;
import com.jerry.www.RecipeApp.service.RecipeService;

class IngredientControllerTest {
	@Mock
	RecipeService recipeService;
	
	@Mock
	IngredientService ingredientService;

	IngredientController controller;

	MockMvc mockMvc;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		controller = new IngredientController(recipeService, ingredientService, null);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	void listOfIngredientsTest() throws Exception {
		// given
		RecipeCommand recipeCommand = new RecipeCommand();
		
		
		//When
		when(recipeService.findCommandById(anyLong())).thenReturn(recipeCommand);
		mockMvc.perform(get("/recipe/1/ingredients"))
		.andExpect(status().isOk())
		.andExpect(view().name("recipe/ingredient/list"))
		.andExpect(model().attributeExists("recipe"));
		
		//then 
		verify(recipeService).findCommandById(anyLong());

	}
	
	@Test
	void showIngredientTest() throws Exception{
		//given 
		IngredientCommand command = new IngredientCommand();
		
		//when 
		when(ingredientService.findByRecipeIdAndIngredientId(anyLong(),anyLong())).thenReturn(command);
		
		//then
		mockMvc.perform(get("/recipe/1/ingredient/2/show"))
		.andExpect(status().isOk())
		.andExpect(view().name("recipe/ingredient/show"))
		.andExpect(model().attributeExists("ingredient"));
		
	}

}