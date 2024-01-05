package unibuc.RecipeManagement.dto;

import lombok.Builder;
import lombok.Data;
import unibuc.RecipeManagement.entity.Ingredient;
import unibuc.RecipeManagement.entity.Recipe;

@Data
@Builder
public class RecipeIngredientCountDto {
    private Integer count;
    private Integer ingredientId;
}
