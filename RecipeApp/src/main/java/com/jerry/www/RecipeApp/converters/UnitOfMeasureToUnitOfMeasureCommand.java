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
public class UnitOfMeasureToUnitOfMeasureCommand implements Converter<UnitOfMesure, UnitOfMesureCommand> {

	@Synchronized
	@Nullable
	@Override
	public UnitOfMesureCommand convert(UnitOfMesure source) {
		if (source == null) {
			return null;
		}
		final UnitOfMesureCommand uomCom = new UnitOfMesureCommand();
		uomCom.setId(source.getId());
		uomCom.setDescription(source.getDescription());
		return uomCom;
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
