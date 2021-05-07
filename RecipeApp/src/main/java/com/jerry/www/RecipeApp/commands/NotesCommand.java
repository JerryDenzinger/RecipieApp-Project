package com.jerry.www.RecipeApp.commands;

import com.jerry.www.RecipeApp.model.Recipe;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NotesCommand {
	private Long id;
	private Recipe recipe;
	private String recipeNotes;
}
