package unibuc.RecipeManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import unibuc.RecipeManagement.entity.Ingredient;

public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {

    @Query(value = "SELECT * FROM Ingredients WHERE name = ?", nativeQuery = true)
    public Ingredient getIngredientByName(String name);
}
