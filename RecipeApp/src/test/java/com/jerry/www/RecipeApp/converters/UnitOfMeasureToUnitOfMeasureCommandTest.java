package com.jerry.www.RecipeApp.converters;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.jerry.www.RecipeApp.commands.UnitOfMesureCommand;
import com.jerry.www.RecipeApp.model.UnitOfMesure;

class UnitOfMeasureToUnitOfMeasureCommandTest {
	public static final String DESCRIPTION = "description";
	public static final Long LONG_VALUE = 1L;

	UnitOfMeasureToUnitOfMeasureCommand converter;

	@BeforeEach
	void setUp() throws Exception {
		converter = new UnitOfMeasureToUnitOfMeasureCommand();
	}

	@Test
	public void nullParameterTest() throws Exception {
		assertNull(converter.convert(null));
	}

	@Test
	public void emptyObjectTest() throws Exception {
		assertNotNull(converter.convert(new UnitOfMesure()));
	}
	
	@Test
	public void converterTest() throws Exception {
		//given 
		UnitOfMesure uom = new UnitOfMesure();
		uom.setId(LONG_VALUE);
		uom.setDescription(DESCRIPTION);
		
		//when 
		UnitOfMesureCommand command = converter.convert(uom);
		
		//then 
		assertNotNull(command);
		assertEquals(LONG_VALUE, command.getId());
		assertEquals(DESCRIPTION, command.getDescription());

	}

}
