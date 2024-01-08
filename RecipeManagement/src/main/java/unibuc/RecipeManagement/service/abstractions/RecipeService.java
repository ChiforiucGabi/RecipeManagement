package unibuc.RecipeManagement.service.abstractions;

import unibuc.RecipeManagement.dto.RecipeDisplayDto;
import unibuc.RecipeManagement.dto.RecipeDto;
import unibuc.RecipeManagement.dto.TagRecipeDto;

import java.util.List;

public interface RecipeService {
    public RecipeDto addRecipe(RecipeDto recipeDto);

    public RecipeDisplayDto getRecipeById(Integer recipeId);

    public RecipeDto addTagsToRecipe(TagRecipeDto dto);

    public List<RecipeDto> getRecipesByTagName(String tagName);

    public RecipeDto editRecipe(RecipeDto dto);
}
