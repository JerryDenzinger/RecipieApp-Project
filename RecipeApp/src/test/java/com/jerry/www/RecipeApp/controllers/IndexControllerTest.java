package com.jerry.www.RecipeApp.controllers;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import com.jerry.www.RecipeApp.service.RecipeService;

class IndexControllerTest {
	@Mock
	RecipeService recipeService;
	
	@Mock
	Model model;
	
	IndexController controller;
	
	@BeforeEach
	public void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		
		controller = new IndexController(recipeService);
	}
	

	@Test
	public void getInexPage() throws Exception {
		String viewName = controller.getIndexPage(model);
		
		assertEquals("index",viewName);
	}

}
