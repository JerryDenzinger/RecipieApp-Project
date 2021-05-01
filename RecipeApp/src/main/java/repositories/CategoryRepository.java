package repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.jerry.www.RecipeApp.model.Category;

public interface CategoryRepository extends CrudRepository<Category, Long>  {
	
	Optional<Category> findByCategoryName(String categoryName);

}
