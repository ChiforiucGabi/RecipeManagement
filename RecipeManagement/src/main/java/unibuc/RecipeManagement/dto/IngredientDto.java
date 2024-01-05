package unibuc.RecipeManagement.dto;

import lombok.Builder;
import lombok.Data;
import unibuc.RecipeManagement.entity.NutritionalValue;
@Data
@Builder
public class IngredientDto {
    private String name;
    private String unitOfMeasure;
}
