package unibuc.RecipeManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import unibuc.RecipeManagement.entity.Ingredient;
import unibuc.RecipeManagement.entity.Recipe;
import unibuc.RecipeManagement.entity.Tag;

public interface TagRepository extends JpaRepository<Tag, Integer> {
    @Query(value = "SELECT * FROM Tags WHERE name = ?", nativeQuery = true)
    public Tag getTagByName(String name);
}
