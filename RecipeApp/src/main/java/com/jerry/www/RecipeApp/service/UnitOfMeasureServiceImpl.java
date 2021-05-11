package com.jerry.www.RecipeApp.service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.jerry.www.RecipeApp.commands.UnitOfMeasureCommand;
import com.jerry.www.RecipeApp.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.jerry.www.RecipeApp.repositories.UnitOfMeasureRepository;

public class UnitOfMeasureServiceImpl implements UnitOfMeasureService {

	private final UnitOfMeasureRepository unitOfMeasureRepository;
	private final UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;

	public UnitOfMeasureServiceImpl(UnitOfMeasureRepository unitOfMeasureRepository,
			UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand) {
		this.unitOfMeasureRepository = unitOfMeasureRepository;
		this.unitOfMeasureToUnitOfMeasureCommand = unitOfMeasureToUnitOfMeasureCommand;
	}

	@Override
	public Set<UnitOfMeasureCommand> listAllUoms() {
		return StreamSupport.stream(unitOfMeasureRepository.findAll().spliterator(), false)
				.map(unitOfMeasureToUnitOfMeasureCommand::convert).collect(Collectors.toSet());
	}

}
