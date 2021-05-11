package com.jerry.www.RecipeApp.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.jerry.www.RecipeApp.commands.UnitOfMeasureCommand;
import com.jerry.www.RecipeApp.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.jerry.www.RecipeApp.model.UnitOfMeasure;
import com.jerry.www.RecipeApp.repositories.UnitOfMeasureRepository;

class UnitOfMeasureServiceImplTest {
	UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;
	UnitOfMeasureService service;

	@Mock
	UnitOfMeasureRepository unitOfMeasureRepository;

	@BeforeEach
	void setUp() throws Exception {
	MockitoAnnotations.openMocks(this);
	
	service = new UnitOfMeasureServiceImpl(unitOfMeasureRepository, unitOfMeasureToUnitOfMeasureCommand);
	}

	@Test
	void listAllUomsTest() {
		//given
		Set<UnitOfMeasure> unitOfMeasures = new HashSet<>();
		UnitOfMeasure uom1 = new UnitOfMeasure();
		uom1.setId(1L);
		unitOfMeasures.add(uom1);
		
		UnitOfMeasure uom2 = new UnitOfMeasure();
		uom1.setId(2L);
		unitOfMeasures.add(uom2);
		
		//when 
		when(unitOfMeasureRepository.findAll()).thenReturn(unitOfMeasures);
		
		Set<UnitOfMeasureCommand> commands = service.listAllUoms();
		
		//then 
		assertEquals(2, commands.size());
		verify(unitOfMeasureRepository).findAll();
		
		
				
	}

}