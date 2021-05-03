package com.jerry.www.RecipeApp.controllers;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jerry.www.RecipeApp.model.Category;
import com.jerry.www.RecipeApp.model.UnitOfMesure;
import com.jerry.www.RecipeApp.repositories.CategoryRepository;
import com.jerry.www.RecipeApp.repositories.UnitOfMesureRepository;

@Controller
public class IndexController {

	private CategoryRepository categoryRepository;
	private UnitOfMesureRepository unitOfMeasureRepository;

	public IndexController(CategoryRepository categoryRepository, UnitOfMesureRepository unitOfMeasureRepository) {
		this.categoryRepository = categoryRepository;
		this.unitOfMeasureRepository = unitOfMeasureRepository;
	}

	@RequestMapping({ "", "/", "/index" })
	public String getIndexPage() {

		Optional<Category> categoryOptional = categoryRepository.findByCategoryName("American");
		Optional<UnitOfMesure> unitOfMeasureOptional = unitOfMeasureRepository.findByDescription("Teaspoon");

		System.out.println("Cat Id is: " + categoryOptional.get().getId());
		System.out.println("UOM ID is: " + unitOfMeasureOptional.get().getId());

		return "index";
	}
	
	@RequestMapping({"/test","test"})
	public String getTest() {
		System.out.println("----------------Test-------------");
		return "test";
	}
}