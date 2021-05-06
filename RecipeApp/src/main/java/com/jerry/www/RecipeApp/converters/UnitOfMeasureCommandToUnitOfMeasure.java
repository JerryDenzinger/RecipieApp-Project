package com.jerry.www.RecipeApp.converters;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import com.jerry.www.RecipeApp.commands.UnitOfMesureCommand;
import com.jerry.www.RecipeApp.model.UnitOfMesure;
import com.sun.istack.Nullable;

import lombok.Synchronized;

@Component
public class UnitOfMeasureCommandToUnitOfMeasure implements Converter<UnitOfMesureCommand, UnitOfMesure> {
	@Synchronized
	@Nullable
	@Override
	public UnitOfMesure convert(UnitOfMesureCommand source) {
		if (source == null) {
			return null;
		}
		final UnitOfMesure uom = new UnitOfMesure();
		uom.setId(source.getId());
		uom.setDescription(source.getDescription());
		return uom;
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
