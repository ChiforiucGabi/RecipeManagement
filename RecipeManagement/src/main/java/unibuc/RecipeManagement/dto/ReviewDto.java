package unibuc.RecipeManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import unibuc.RecipeManagement.constants.Constants;
import unibuc.RecipeManagement.validator.OnlyLetters;
import unibuc.RecipeManagement.validator.RatingValue;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDto {
    private String comment;
    @RatingValue(message = Constants.VALUE_OUT_OF_RANGE)
    private Integer rating;
    private Integer recipeId;
}
