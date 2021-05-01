package controllers;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jerry.www.RecipeApp.model.Category;
import com.jerry.www.RecipeApp.model.UnitOfMesure;

import repositories.CategoryRepository;
import repositories.UnitOfMesureRepository;

@Controller
public class indexController {

	private CategoryRepository categoryRepository;
	private UnitOfMesureRepository unitOfMesureRepository;

	public indexController(CategoryRepository categoryRepository, UnitOfMesureRepository unitOfMesureRepository) {
		this.categoryRepository = categoryRepository;
		this.unitOfMesureRepository = unitOfMesureRepository;
	}

	@RequestMapping({ "", "/", "/index" })
	public String getIndexPage() {
		
		System.out.println("Helloooo!!!!");
		
		Optional<Category> categoryOptional = categoryRepository.findByCategoryName("American");
		Optional<UnitOfMesure> unitOptional = unitOfMesureRepository.findByDescription("Pinch");
		
		System.out.println("Cat id is: " + categoryOptional.get().getId());
		System.out.println("Unit id is: " + unitOptional.get().getId());
		return "index";
	}

}
