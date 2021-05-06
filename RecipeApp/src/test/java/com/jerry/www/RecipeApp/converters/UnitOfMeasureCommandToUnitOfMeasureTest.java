package com.jerry.www.RecipeApp.converters;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.jerry.www.RecipeApp.commands.UnitOfMesureCommand;
import com.jerry.www.RecipeApp.model.UnitOfMesure;

class UnitOfMeasureCommandToUnitOfMeasureTest {
	public static final String DESCRIPTION = "description";
	public static final Long LONG_VALUE = 1L;

	UnitOfMeasureCommandToUnitOfMeasure converter;

	@BeforeEach
	public void setUp() throws Exception {
		converter = new UnitOfMeasureCommandToUnitOfMeasure();
	}

	@Test
	public void nullParameterTest() throws Exception {
		assertNull(converter.convert(null));
	}

	@Test
	public void emptyObjectTest() throws Exception {
		assertNotNull(converter.convert(new UnitOfMesureCommand()));
	}

	@Test
	public void onverterTest() throws Exception {
		//given 
		UnitOfMesureCommand command = new UnitOfMesureCommand();
		command.setId(LONG_VALUE);
		command.setDescription(DESCRIPTION);
		
		//when 
		UnitOfMesure uom = converter.convert(command);
		
		//then 
		assertNotNull(uom);
		assertEquals(LONG_VALUE, uom.getId());
		assertEquals(DESCRIPTION, uom.getDescription());

	}

}
