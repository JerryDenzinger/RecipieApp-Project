package com.jerry.www.RecipeApp.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import com.jerry.www.RecipeApp.model.Recipe;
import com.jerry.www.RecipeApp.repositories.RecipeRepository;

class ImageServiceImplTest {
	@Mock
	RecipeRepository recipeRepository;
	
	@Mock
	ImageService imageService;
	

	@BeforeEach
	public void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		
		imageService = new ImageServiceImpl(recipeRepository);
	}

	@Test
	public void test() throws Exception {
		//given 
		Long id = 1L;
		MultipartFile multipartFile = new MockMultipartFile("imagefile", "testing.txt,","text/plain","Spring Framework".getBytes());
		
		Recipe recipe = new Recipe();
		recipe.setId(id);
		Optional<Recipe> recipeOptional = Optional.of(recipe);
		
		when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);
		
		ArgumentCaptor<Recipe> argumentCaptor = ArgumentCaptor.forClass(Recipe.class);
		
		//When 
		imageService.saveImageFileToRecipeId(id, multipartFile);
		
		//Then 
		verify(recipeRepository).save(argumentCaptor.capture());
		Recipe saveRecipe = argumentCaptor.getValue();
		assertEquals(multipartFile.getBytes().length,saveRecipe.getImage().length);
	}

}
