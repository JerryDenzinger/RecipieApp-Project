package com.jerry.www.RecipeApp.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.jerry.www.RecipeApp.repositories.UnitOfMesureRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UnitOfMesureTest {
	
	@Autowired
	UnitOfMesureRepository unitOfMesureRepository;

	@BeforeEach
	public void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);

	}

	@Test
	void findByDescription() {
		Optional<UnitOfMesure> uomOptional = unitOfMesureRepository.findByDescription("Teaspoon");
		
		assertEquals("Teaspoon", uomOptional.get().getDescription());
	}
	
	@Test
	void findByDescriptionCup() {
		Optional<UnitOfMesure> uomOptional = unitOfMesureRepository.findByDescription("Cup");
		
		assertEquals("Cup", uomOptional.get().getDescription());
	}

}
