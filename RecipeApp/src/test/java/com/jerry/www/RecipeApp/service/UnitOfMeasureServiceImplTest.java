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
import com.jerry.www.RecipeApp.controllers.ImageController;
import com.jerry.www.RecipeApp.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.jerry.www.RecipeApp.model.UnitOfMeasure;
import com.jerry.www.RecipeApp.repositories.UnitOfMeasureRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class UnitOfMeasureServiceImplTest {
	@Mock
	UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;

	@Mock
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
		// given
		Set<UnitOfMeasure> unitOfMeasures = new HashSet<>();
		UnitOfMeasure uom1 = new UnitOfMeasure();
		uom1.setId(1L);
		unitOfMeasures.add(uom1);
		log.debug("unitofmeasures size " + unitOfMeasures.size());

		UnitOfMeasure uom2 = new UnitOfMeasure();
		uom2.setId(2L);
		unitOfMeasures.add(uom2);
		log.debug("unitofmeasures size " + unitOfMeasures.size());
		
		UnitOfMeasure uom3 = new UnitOfMeasure();
		uom3.setId(3L);
		unitOfMeasures.add(uom3);
		log.debug("unitofmeasures size " + unitOfMeasures.size());

		// when
		when(unitOfMeasureRepository.findAll()).thenReturn(unitOfMeasures);

		Set<UnitOfMeasureCommand> commands = service.listAllUoms();
		System.out.println(commands.isEmpty());

		// then
		log.debug("commands size " + commands.size());
		assertEquals(3, commands.size());
		verify(unitOfMeasureRepository).findAll();

	}

}
