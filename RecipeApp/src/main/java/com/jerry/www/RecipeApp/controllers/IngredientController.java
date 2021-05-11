package com.jerry.www.RecipeApp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jerry.www.RecipeApp.service.IngredientService;
import com.jerry.www.RecipeApp.service.RecipeService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class IngredientController {
	private final RecipeService recipeService;
	private final IngredientService ingredientService;

	public IngredientController(RecipeService recipeService, IngredientService ingredientService) {
		this.recipeService = recipeService;
		this.ingredientService = ingredientService;
	}
	
	@GetMapping
	@RequestMapping("recipe/{id}/ingredients")
	public String getListOfIngredients(@PathVariable String id,Model model) {
		log.debug("Showing List of ingredients from recipe ID= " + id);
		
		//use command object to avoid lazy load errors in thymeleaf
		model.addAttribute("recipe",recipeService.findCommandById(Long.valueOf(id)));
		
		return "recipe/ingredient/list";
	}
	
	@RequestMapping("recipe/{recipeId}/ingredient/{ingredientId}/show")
	public String getIngredient(@PathVariable String recipeId, @PathVariable String ingredientId, Model model){
		log.debug("Showing ingredient with Id = " + ingredientId + " from recipe with id" + recipeId);
		
		// use command object to avoid lazy load errors in thymeleaf
		model.addAttribute("ingredient",ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(recipeId), Long.valueOf(ingredientId)));
		
		return "recipe/ingredient/show";
	}
	
	

}
