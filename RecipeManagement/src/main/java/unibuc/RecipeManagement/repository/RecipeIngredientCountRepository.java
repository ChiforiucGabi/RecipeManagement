package unibuc.RecipeManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import unibuc.RecipeManagement.entity.RecipeIngredientCount;

public interface RecipeIngredientCountRepository extends JpaRepository<RecipeIngredientCount, Integer>{
}
