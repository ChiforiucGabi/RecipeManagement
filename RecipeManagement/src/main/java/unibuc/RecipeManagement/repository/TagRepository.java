package unibuc.RecipeManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import unibuc.RecipeManagement.entity.Ingredient;
import unibuc.RecipeManagement.entity.Recipe;
import unibuc.RecipeManagement.entity.Tag;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Integer> {
    @Query(value = "SELECT * FROM Tags WHERE name = ?", nativeQuery = true)
    public Tag getTagByName(String name);

    @Query(value = "SELECT T.id as id, T.name as name FROM Tags T " +
            "INNER JOIN recipe_tag RT " +
            "ON T.id = RT.tag_id " +
            "INNER JOIN recipes R " +
            "ON R.id = RT.recipe_id " +
            "WHERE R.id = ?", nativeQuery = true)
    public List<Tag> getRecipeTags(Integer recipeId);
}
