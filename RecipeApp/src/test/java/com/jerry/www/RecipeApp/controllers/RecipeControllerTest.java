package com.jerry.www.RecipeApp.controllers;

import static org.mockito.ArgumentMatchers.anyLong;
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

import com.jerry.www.RecipeApp.model.Recipe;
import com.jerry.www.RecipeApp.service.RecipeService;

class RecipeControllerTest {
	@Mock
	RecipeService recipeService;
	
	RecipeController controller;
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		
		controller = new RecipeController(recipeService);
	}

	@Test
	void getRecipeTest()  throws Exception{
		Recipe recipe = new Recipe();
		recipe.setId(1L);
		
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
		
		when(recipeService.findById(anyLong())).thenReturn(recipe);
		
		mockMvc.perform(get("/recipe/show/1"))
		.andExpect(status().isOk())
		.andExpect(view().name("recipe/show"))
		.andExpect(model().attributeExists("recipe"));
		
	}

}