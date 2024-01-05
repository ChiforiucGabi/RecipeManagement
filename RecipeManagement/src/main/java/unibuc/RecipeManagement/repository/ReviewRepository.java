package unibuc.RecipeManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import unibuc.RecipeManagement.entity.Recipe;
import unibuc.RecipeManagement.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
}
