package com.jerry.www.RecipeApp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jerry.www.RecipeApp.commands.IngredientCommand;
import com.jerry.www.RecipeApp.commands.RecipeCommand;
import com.jerry.www.RecipeApp.commands.UnitOfMeasureCommand;
import com.jerry.www.RecipeApp.service.IngredientService;
import com.jerry.www.RecipeApp.service.RecipeService;
import com.jerry.www.RecipeApp.service.UnitOfMeasureService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class IngredientController {
	private final RecipeService recipeService;
	private final IngredientService ingredientService;
	private final UnitOfMeasureService unitOfMeasureService; 


	
	public IngredientController(RecipeService recipeService, IngredientService ingredientService,
			UnitOfMeasureService unitOfMeasureService) {
		this.recipeService = recipeService;
		this.ingredientService = ingredientService;
		this.unitOfMeasureService = unitOfMeasureService;
	}

	@GetMapping
	@RequestMapping("recipe/{id}/ingredients")
	public String getListOfIngredients(@PathVariable String id,Model model) {
		log.debug("Showing List of ingredients from recipe ID= " + id);
		
		//use command object to avoid lazy load errors in thymeleaf
		model.addAttribute("recipe",recipeService.findCommandById(Long.valueOf(id)));
		
		return "recipe/ingredient/list";
	}
	
	@GetMapping
	@RequestMapping("recipe/{recipeId}/ingredient/{ingredientId}/show")
	public String getIngredient(@PathVariable String recipeId, @PathVariable String ingredientId, Model model){
		log.debug("Showing ingredient with Id = " + ingredientId + " from recipe with id " + recipeId);
		
		model.addAttribute("ingredient",ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(recipeId), Long.valueOf(ingredientId)));
		
		
		return "recipe/ingredient/show";
	}
	
	@GetMapping
	@RequestMapping("recipe/{recipeId}/ingredient/new")
	public String newIngredient(@PathVariable String recipeId ,Model model) {
		// make sure that there is a valid id 
		RecipeCommand recipeCommand = recipeService.findCommandById(Long.valueOf(recipeId));
		
		//Need to return back parent id for hidden form 
		IngredientCommand ingredientCommand = new IngredientCommand();
		ingredientCommand.setRecipeId(Long.valueOf(recipeId));
		model.addAttribute("ingredient" , ingredientCommand);
		
		// init uom
		ingredientCommand.setUnitOfMeasure(new UnitOfMeasureCommand());
		
		model.addAttribute("uomList",unitOfMeasureService.listAllUoms());
		
		return "recipe/ingredient/ingredientform";
		
	}
	
	@GetMapping
	@RequestMapping("recipe/{recipeId}/ingredient/{ingredientId}/update")
	public String updateIgredient(@PathVariable String recipeId, @PathVariable String ingredientId, Model model){
		log.debug("Update ingredient with Id = " + ingredientId + " from recipe with id " + recipeId);
		
		model.addAttribute("ingredient" , ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(recipeId),Long.valueOf(ingredientId)));
		
		model.addAttribute("uomList",unitOfMeasureService.listAllUoms());
		
		return "recipe/ingredient/ingredientform";
		
		
	}
	
    @PostMapping("recipe/{recipeId}/ingredient")
    public String saveOrUpdate(@ModelAttribute IngredientCommand command){
        IngredientCommand savedCommand = ingredientService.saveIngredientCommand(command);

        log.debug("saved receipe id:" + savedCommand.getRecipeId());
        log.debug("saved ingredient id:" + savedCommand.getId());

        return "redirect:/recipe/" + savedCommand.getRecipeId() + "/ingredient/" + savedCommand.getId() + "/show";
    }
	
	

}
