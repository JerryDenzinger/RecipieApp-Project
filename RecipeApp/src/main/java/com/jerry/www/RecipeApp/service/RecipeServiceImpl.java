package com.jerry.www.RecipeApp.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.jerry.www.RecipeApp.repositories.RecipeRepository;

import lombok.extern.slf4j.Slf4j;

import com.jerry.www.RecipeApp.model.Recipe;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {
	private final RecipeRepository recipeRepository;

	public RecipeServiceImpl(RecipeRepository recipeRepository) {
		this.recipeRepository = recipeRepository;
	}

	@Override
	public Set<Recipe> getRecipes() {
		log.debug("I´m in the Service");
		Set<Recipe> recipeSet = new HashSet<>();

		recipeRepository.findAll().iterator().forEachRemaining(recipeSet::add);

		return recipeSet;

	}

	@Override
	public Recipe findById(Long id) {
		Optional<Recipe> recipe = recipeRepository.findById(id);
		if (!recipe.isPresent()) {
			throw new RuntimeException("Recipe Not Found");
		}
		return recipe.get();
	}
}
