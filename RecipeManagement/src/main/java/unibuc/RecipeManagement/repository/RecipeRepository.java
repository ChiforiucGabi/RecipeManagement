package unibuc.RecipeManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import unibuc.RecipeManagement.entity.Recipe;

public interface RecipeRepository extends JpaRepository<Recipe, Integer> {
}
