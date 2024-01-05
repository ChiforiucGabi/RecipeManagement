package unibuc.RecipeManagement.mapper;

import unibuc.RecipeManagement.dto.IngredientDto;
import unibuc.RecipeManagement.entity.Ingredient;

public class IngredientMapper {

    public static IngredientDto convertToDto(Ingredient ingredient){
        return IngredientDto.builder()
                .name(ingredient.getName())
                .unitOfMeasure(ingredient.getUnitOfMeasure())
                .build();
    }

    public static Ingredient convertToIngredient (IngredientDto ingredientDto){
        return Ingredient.builder()
                .name(ingredientDto.getName())
                .unitOfMeasure(ingredientDto.getUnitOfMeasure())
                .build();
    }
}
