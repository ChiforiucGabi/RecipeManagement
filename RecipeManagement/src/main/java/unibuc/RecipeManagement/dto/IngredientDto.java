package unibuc.RecipeManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import unibuc.RecipeManagement.entity.NutritionalValue;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IngredientDto {
    private String name;
    private String unitOfMeasure;
}
