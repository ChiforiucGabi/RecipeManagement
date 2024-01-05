package unibuc.RecipeManagement.dto;

import jakarta.validation.Valid;
import lombok.Builder;
import lombok.Data;
import unibuc.RecipeManagement.constants.Constants;
import unibuc.RecipeManagement.validator.OnlyLetters;

@Data
@Builder
public class RecipeDto {
    @OnlyLetters(message = Constants.RECIPE_NAME_INVALID_FORMAT)
    private String name;
    private String description;
    private Integer cookingTime;
}
