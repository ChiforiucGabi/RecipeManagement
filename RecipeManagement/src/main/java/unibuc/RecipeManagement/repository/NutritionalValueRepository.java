package unibuc.RecipeManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import unibuc.RecipeManagement.dto.RecipeNutritionalValuesDto;
import unibuc.RecipeManagement.entity.NutritionalValue;

public interface NutritionalValueRepository extends JpaRepository<NutritionalValue, Integer> {

    @Query(value = "SELECT R.name as recipeName, SUM(RIC.count * N.calories) as calories, SUM(RIC.count * N.carbohydrates) as carbohydrates,  " +
            "SUM(RIC.count * N.fat) as fat, SUM(RIC.count * N.fiber) as  fiber, SUM(RIC.count * N.protein) as protein " +
            "FROM recipes R " +
            "INNER JOIN recipe_ingredients_count RIC  " +
            "ON R.id = RIC.recipe_id  " +
            "INNER JOIN ingredients I  " +
            "ON RIC.ingredient_id = I.id  " +
            "INNER JOIN nutritional_values N " +
            "ON I.nutritional_value_id = N.id " +
            "WHERE R.id = ? " +
            "GROUP BY R.id, R.name", nativeQuery = true)
    public RecipeNutritionalValuesDto getRecipeNutritionalValues(Integer recipeId);
}
