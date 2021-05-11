package com.jerry.www.RecipeApp.service;

import java.util.Set;

import com.jerry.www.RecipeApp.commands.UnitOfMeasureCommand;

public interface UnitOfMeasureService {

	Set<UnitOfMeasureCommand> listAllUoms();

}
