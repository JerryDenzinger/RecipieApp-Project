package com.jerry.www.RecipeApp.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

	void saveImageFileToRecipeId(Long recipeId, MultipartFile file);

}
