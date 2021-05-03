package com.jerry.www.RecipeApp.controllers;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jerry.www.RecipeApp.service.RecipeService;



@Controller
public class IndexController {
	
	private final RecipeService recipeService;
	
	public IndexController(RecipeService recipeService) {
		this.recipeService = recipeService;
	}

	@RequestMapping({ "", "/", "/index" })
	public String getIndexPage(Model model) {
		model.addAttribute("recipes", recipeService.getRecipes());
		return "index";
	}
	
	@RequestMapping({"/test","test"})
	public String getTest() {
		System.out.println("----------------Test-------------");
		return "test";
	}
}
