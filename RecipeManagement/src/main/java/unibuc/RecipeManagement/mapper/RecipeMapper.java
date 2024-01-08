package unibuc.RecipeManagement.mapper;

import unibuc.RecipeManagement.dto.RecipeDisplayDto;
import unibuc.RecipeManagement.dto.RecipeDto;
import unibuc.RecipeManagement.entity.Recipe;

public class RecipeMapper {

    public static RecipeDto convertToDto(Recipe recipe){
        return RecipeDto.builder()
                .name(recipe.getName())
                .description(recipe.getDescription())
                .cookingTime(recipe.getCookingTime())
                .id(recipe.getId())
                .build();
    }

    public static Recipe convertToRecipe (RecipeDto recipeDto){
        return Recipe.builder()
                .name(recipeDto.getName())
                .description(recipeDto.getDescription())
                .cookingTime(recipeDto.getCookingTime())
                .build();
    }

    public static RecipeDisplayDto convertToDisplayDto(Recipe recipe)
    {
        return RecipeDisplayDto.builder()
                .name(recipe.getName())
                .description(recipe.getDescription())
                .cookingTime(recipe.getCookingTime())
                .build();
    }
}
