package unibuc.RecipeManagement.mapper;

import unibuc.RecipeManagement.dto.RecipeIngredientCountDto;
import unibuc.RecipeManagement.entity.RecipeIngredientCount;

public class RecipeIngredientCountMapper {
    public static RecipeIngredientCountDto convertToDto(RecipeIngredientCount recipeIngredientCount){
        return RecipeIngredientCountDto.builder()
                .count(recipeIngredientCount.getCount())
                .build();
    }
    public static RecipeIngredientCount convertToRecipeIngredientCount(RecipeIngredientCountDto recipeIngredientCountDto){
        return RecipeIngredientCount.builder()
                .count(recipeIngredientCountDto.getCount())
                .build();
    }
}
