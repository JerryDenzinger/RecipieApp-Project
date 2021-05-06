package com.jerry.www.RecipeApp.commands;

import java.math.BigDecimal;

import com.jerry.www.RecipeApp.model.Recipe;
import com.jerry.www.RecipeApp.model.UnitOfMesure;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class IngredientCommand {
	private Long id;
	private String description;
	private BigDecimal amount;
	private UnitOfMesure uom;
	private Recipe recipe;
	
}