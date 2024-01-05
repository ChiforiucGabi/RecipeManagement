package unibuc.RecipeManagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="RecipeIngredientsCount")
public class RecipeIngredientCount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer count;

    @ManyToOne()
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;

    @ManyToOne()
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;
}
