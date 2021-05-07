package com.jerry.www.RecipeApp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jerry.www.RecipeApp.commands.RecipeCommand;
import com.jerry.www.RecipeApp.service.RecipeService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class NewRecipeController {

	private final RecipeService recipeService;

	public NewRecipeController(RecipeService recipeService) {
		this.recipeService = recipeService;
	}

	@RequestMapping("new")
	public String getNewRecipeForm(Model model) {
		model.addAttribute("recipe", new RecipeCommand());
		return "recipe/recipeform";

	}

	@PostMapping
	@RequestMapping("recipe")
	public String saveOrUpdate(@ModelAttribute RecipeCommand command) {
		RecipeCommand savedCommand = recipeService.saveRecipeCommand(command);
		return "redirect:/recipe/show/" + savedCommand.getId();
	}

}
