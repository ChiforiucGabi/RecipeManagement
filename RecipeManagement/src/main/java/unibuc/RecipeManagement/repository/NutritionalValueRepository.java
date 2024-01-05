package unibuc.RecipeManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import unibuc.RecipeManagement.entity.NutritionalValue;
import unibuc.RecipeManagement.entity.Recipe;

public interface NutritionalValueRepository extends JpaRepository<NutritionalValue, Integer> {
}
