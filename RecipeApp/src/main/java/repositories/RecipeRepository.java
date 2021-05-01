package repositories;

import org.springframework.data.repository.CrudRepository;

import com.jerry.www.RecipeApp.model.Recipe;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {

}
