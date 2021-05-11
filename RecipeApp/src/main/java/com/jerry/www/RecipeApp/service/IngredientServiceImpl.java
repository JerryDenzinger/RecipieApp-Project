package com.jerry.www.RecipeApp.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jerry.www.RecipeApp.commands.IngredientCommand;
import com.jerry.www.RecipeApp.converters.IngredientCommandToIngredient;
import com.jerry.www.RecipeApp.converters.IngredientToIngredientCommand;
import com.jerry.www.RecipeApp.model.Ingredient;
import com.jerry.www.RecipeApp.model.Recipe;
import com.jerry.www.RecipeApp.repositories.RecipeRepository;
import com.jerry.www.RecipeApp.repositories.UnitOfMeasureRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {

	private final RecipeRepository recipeRepository;
	private final UnitOfMeasureRepository unitOfMeasureRepository;
	private final IngredientToIngredientCommand ingredientToIngredientCommand;
	private final IngredientCommandToIngredient ingredientCommandToIngredient;

	public IngredientServiceImpl(RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository,
			IngredientToIngredientCommand ingredientToIngredientCommand,
			IngredientCommandToIngredient ingredientCommandToIngredient) {
		this.recipeRepository = recipeRepository;
		this.unitOfMeasureRepository = unitOfMeasureRepository;
		this.ingredientToIngredientCommand = ingredientToIngredientCommand;
		this.ingredientCommandToIngredient = ingredientCommandToIngredient;
	}

	@Override
	public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {
		Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);
		if (!recipeOptional.isPresent()) {
			throw new RuntimeException("Recipe Not Found");
		}
		Recipe recipe = recipeOptional.get();

		Optional<IngredientCommand> ingredientCommandOptional = recipe.getIngredients().stream()
				.filter(ingredient -> ingredient.getId().equals(ingredientId))
				.map(ingredient -> ingredientToIngredientCommand.convert(ingredient)).findFirst();

		if (!ingredientCommandOptional.isPresent()) {
			// TODO implement error handling
			log.debug("ingredient id not found " + ingredientId);
		}
		return ingredientCommandOptional.get();
	}

	@Override
	public IngredientCommand saveIngredientCommand(IngredientCommand command) {
		Optional<Recipe> recipeOptional = recipeRepository.findById(command.getRecipeId());

		if (!recipeOptional.isPresent()) {
			log.debug("Recipe not found for id = " + command.getRecipeId());
			return new IngredientCommand();
		} else {
			Recipe recipe = recipeOptional.get();

			Optional<Ingredient> ingredientOptional = recipe.getIngredients().stream()
					.filter(ingredient -> ingredient.getId().equals(command.getId())).findFirst();

			if (ingredientOptional.isPresent()) {
				Ingredient ingredientFound = ingredientOptional.get();
				ingredientFound.setDescription(command.getDescription());
				ingredientFound.setAmount(command.getAmount());
				ingredientFound.setUnitOfMeasure(unitOfMeasureRepository.findById(command.getUnitOfMeasure().getId())
						.orElseThrow(() -> new RuntimeException("UOM NOT Found")));
			} else {
				recipe.addIngredient(ingredientCommandToIngredient.convert(command));
			}

			Recipe savedRecipe = recipeRepository.save(recipe);

			return ingredientToIngredientCommand.convert(savedRecipe.getIngredients().stream()
					.filter(recipeIgredients -> recipeIgredients.getId().equals(command.getId())).findFirst().get());
		}

	}

}
