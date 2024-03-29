package unibuc.RecipeManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import unibuc.RecipeManagement.constants.Constants;
import unibuc.RecipeManagement.validator.OnlyLetters;
import unibuc.RecipeManagement.validator.RatingValue;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecipeDto {
    private Integer id;
    @OnlyLetters(message = Constants.RECIPE_NAME_INVALID_FORMAT)
    private String name;
    private String description;
    private Integer cookingTime;
    private List<RecipeIngredientCountDto> ingredients;
}
