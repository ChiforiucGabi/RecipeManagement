package unibuc.RecipeManagement.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import unibuc.RecipeManagement.dto.RecipeIngredientsDto;
import unibuc.RecipeManagement.entity.Recipe;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Integer> {

    @Query(value = "SELECT I.name as name, RIC.count as count, I.unit_of_measure as unitOfMeasure " +
            "FROM recipes R " +
            "INNER JOIN recipe_ingredients_count RIC   " +
            "ON R.id = RIC.recipe_id " +
            "INNER JOIN ingredients I  " +
            "ON RIC.ingredient_id = I.id  " +
            "WHERE R.id = ?", nativeQuery = true)
    public List<RecipeIngredientsDto> getRecipeIngredientsById(Integer id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO recipe_tag VALUES (?1, ?2)", nativeQuery = true)
    public abstract void addTag(Integer recipeId, Integer TagId);

    @Query(value = "SELECT R.id as id, R.name as name, R.cooking_time as cooking_time, " +
            "R.description as description FROM recipe_tag RT " +
            " INNER JOIN recipes R " +
            " ON R.id = RT.recipe_id " +
            " WHERE RT.tag_id = ?", nativeQuery = true)
    public List<Recipe> getRecipeByTagId(Integer id);
}
