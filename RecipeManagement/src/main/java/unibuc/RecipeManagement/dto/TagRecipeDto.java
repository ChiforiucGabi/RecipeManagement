package unibuc.RecipeManagement.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class TagRecipeDto {
    private List<Integer> tagIds;

    private Integer recipeId;
}
