package unibuc.RecipeManagement.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NutritionalValueDto {

    private Integer ingredientId;
    private Integer calories;
    private Integer protein;
    private Integer carbohydrates;
    private Integer fat;
    private Integer fiber;
}
