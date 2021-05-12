package com.jerry.www.RecipeApp.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.jerry.www.RecipeApp.service.ImageService;

public class ImageControllerTest {
	@Mock
	ImageService imageservice;
	
	ImageController controller;
	
	MockMvc mockMvc;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		
		controller = new ImageController(imageservice);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void handleImagePostTest() throws Exception{
		MockMultipartFile multipartFile = 
				new MockMultipartFile("file","testing.txt","text/plain","Spring Frmework".getBytes());
		
		this.mockMvc.perform(multipart("/recipe/1/image").file(multipartFile))
		.andExpect(status().isFound())
		.andExpect(header().string("Location", "/"));
	}

}
