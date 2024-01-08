package unibuc.RecipeManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import unibuc.RecipeManagement.entity.Ingredient;
import unibuc.RecipeManagement.entity.Recipe;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecipeIngredientCountDto {
    private Integer count;
    private Integer ingredientId;
}
