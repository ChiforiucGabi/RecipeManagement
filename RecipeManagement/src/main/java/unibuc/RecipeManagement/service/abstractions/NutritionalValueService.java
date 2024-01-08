package unibuc.RecipeManagement.service.abstractions;

import unibuc.RecipeManagement.dto.NutritionalValueDto;
import unibuc.RecipeManagement.dto.RecipeNutritionalValuesDto;

public interface NutritionalValueService extends GenericService<NutritionalValueDto>{
    public RecipeNutritionalValuesDto getRecipeNutritionalValues(Integer recipeId);
}
