package unibuc.RecipeManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecipeDisplayDto {

    private String name;

    private Integer cookingTime;

    private String description;

    private List<RecipeIngredientsDto> ingredients;

    private List<TagDto> tags;
}
