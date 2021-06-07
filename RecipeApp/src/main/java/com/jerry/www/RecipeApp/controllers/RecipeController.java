package com.jerry.www.RecipeApp.controllers;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.jerry.www.RecipeApp.commands.RecipeCommand;
import com.jerry.www.RecipeApp.exceptions.NotFoundException;
import com.jerry.www.RecipeApp.service.RecipeService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class RecipeController {

	private static final String RECIPE_RECIPEFORM_URL = "recipe/recipeform";
	private final RecipeService recipeService;

	public RecipeController(RecipeService recipeService) {
		this.recipeService = recipeService;
	}

	@GetMapping("recipe/{id}/show")
	public String showById(@PathVariable String id,Model model) {
		log.debug("Recipe Controller show");
		model.addAttribute("recipe", recipeService.findById(Long.parseLong(id)));
		return "recipe/show";
	}
	
	@GetMapping("recipe/new")
	public String getNewRecipeForm(Model model) {
		log.debug("Recipe Controller recipe form new ");
		model.addAttribute("recipe", new RecipeCommand());
		return RECIPE_RECIPEFORM_URL;

	}
	
	@GetMapping("recipe/{id}/copy")
	public String getCopyFromRecipe(@PathVariable String id,Model model) {
		log.debug("Recipe Controller recipe form copy");
		model.addAttribute("recipe" , recipeService.findCommandById(Long.valueOf(id)));
		model.addAttribute("recipeCopy", new RecipeCommand());
		return "recipe/recipeCopyform";

	}
	
	
	@GetMapping("recipe/{id}/update")
	public String updateRecipe(@PathVariable String id,Model model) {
		log.debug("Recipe Controller update");
		model.addAttribute("recipe" , recipeService.findCommandById(Long.valueOf(id)));
		return RECIPE_RECIPEFORM_URL;
	}
	
	@GetMapping("recipe/{id}/delete")
	public String deleteById(@PathVariable String id,Model model) {
		log.debug("Recipe Controller Delete by id = " + id );
		recipeService.deleteById(Long.valueOf(id));
		return "redirect:/";
	}
	
	@PostMapping("recipe")
	public String saveOrUpdate(@Valid @ModelAttribute ("recipe") RecipeCommand command, BindingResult bindingResult) {
		if(command.getId() != null) {
		log.debug(command.getId().toString());
		}
		else {
			log.debug("Command getId() = null");
		}
		if (bindingResult.hasErrors()) {
			bindingResult.getAllErrors().forEach(objectError -> {
				log.debug(objectError.toString());
			});
			
			return RECIPE_RECIPEFORM_URL;
		}
		log.debug(" New Recipe Controller show saveOrUpdate");
		RecipeCommand savedCommand = recipeService.saveRecipeCommand(command);
		return "redirect:/recipe/" + savedCommand.getId() +"/show";
	}
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(NotFoundException.class)
	public ModelAndView handleNotFound(Exception exception) {
		log.error("Handling not found exception");
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("404error");
		modelAndView.addObject("exception" ,exception);
		
		return modelAndView;
	}
	
}
