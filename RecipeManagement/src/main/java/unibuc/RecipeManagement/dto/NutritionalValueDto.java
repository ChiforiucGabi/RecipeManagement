package unibuc.RecipeManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NutritionalValueDto {

    private Integer ingredientId;
    private Integer calories;
    private Integer protein;
    private Integer carbohydrates;
    private Integer fat;
    private Integer fiber;
}
