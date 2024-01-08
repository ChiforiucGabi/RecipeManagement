package unibuc.RecipeManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import unibuc.RecipeManagement.entity.RecipeIngredientCount;

public interface RecipeIngredientCountRepository extends JpaRepository<RecipeIngredientCount, Integer>{


    @Query(value = "SELECT * FROM recipe_ingredients_count WHERE recipe_id=?1 and ingredient_id=?2", nativeQuery = true)
    public RecipeIngredientCount getRecipeIngredientCountByRecipeId(Integer recipeId, Integer ingredientId);
}
