package com.jerry.www.RecipeApp.converters;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import com.jerry.www.RecipeApp.commands.IngredientCommand;
import com.jerry.www.RecipeApp.model.Ingredient;
import com.sun.istack.Nullable;

import lombok.Synchronized;

@Component
public class IngredientToIngredientCommand implements Converter<Ingredient, IngredientCommand> {

	@Synchronized
	@Nullable
	@Override
	public IngredientCommand convert(Ingredient source) {
		if (source == null) {
		return null;
	}
	final IngredientCommand  ingredientCom = new IngredientCommand();
	ingredientCom.setId(source.getId());
	ingredientCom.setDescription(source.getDescription());
	ingredientCom.setAmount(source.getAmount());
	ingredientCom.setRecipe(source.getRecipe());
	ingredientCom.setUom(source.getUom());
	return ingredientCom;
	}

	@Override
	public JavaType getInputType(TypeFactory typeFactory) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JavaType getOutputType(TypeFactory typeFactory) {
		// TODO Auto-generated method stub
		return null;
	}

}
