package com.jerry.www.RecipeApp.commands;

import java.util.Set;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryCommand {
	private Long id;
	private String categoryName;
	private Set<RecipeCommand> recipes;
}
