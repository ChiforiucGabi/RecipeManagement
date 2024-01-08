package unibuc.RecipeManagement.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import unibuc.RecipeManagement.entity.Ingredient;

public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {

    @Query(value = "SELECT * FROM Ingredients WHERE name = ?", nativeQuery = true)
    public Ingredient getIngredientByName(String name);

    @Modifying
    @Transactional
    @Query(value = "UPDATE ingredients SET nutritional_value_id =?1 WHERE id = ?2", nativeQuery = true)
    public abstract void updateNutritionalValues(Integer nutritionalValueId, Integer ingredientId);
}
