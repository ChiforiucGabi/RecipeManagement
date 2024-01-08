package unibuc.RecipeManagement.service.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unibuc.RecipeManagement.constants.Constants;
import unibuc.RecipeManagement.dto.NutritionalValueDto;
import unibuc.RecipeManagement.dto.RecipeNutritionalValuesDto;
import unibuc.RecipeManagement.entity.NutritionalValue;
import unibuc.RecipeManagement.exception.DataNotFoundException;
import unibuc.RecipeManagement.mapper.NutritionalValueMapper;
import unibuc.RecipeManagement.mapper.RecipeMapper;
import unibuc.RecipeManagement.repository.IngredientRepository;
import unibuc.RecipeManagement.repository.NutritionalValueRepository;
import unibuc.RecipeManagement.repository.RecipeIngredientCountRepository;
import unibuc.RecipeManagement.repository.RecipeRepository;
import unibuc.RecipeManagement.service.abstractions.NutritionalValueService;

@Service
public class NutritionalValueServiceImpl implements NutritionalValueService {
    @Autowired
    private NutritionalValueRepository nutritionalValueRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private RecipeRepository recipeRepository;

    @Override
    public NutritionalValueDto save(NutritionalValueDto entity) {
        var ingredientOptional = ingredientRepository.findById(entity.getIngredientId());
        if(ingredientOptional.isEmpty())
        {
            throw new DataNotFoundException(Constants.INGREDIENT_NOT_FOUND);
        }

        var nutritionalValue = NutritionalValueMapper.convertToNutritionalValue(entity);

        nutritionalValue.setIngredient(ingredientOptional.get());
        var ingredient = ingredientOptional.get();
        ingredient.setValues(nutritionalValue);
        ingredientRepository.save(ingredient);

        return NutritionalValueMapper.convertToDto(nutritionalValue);
    }

    @Override
    public RecipeNutritionalValuesDto getRecipeNutritionalValues(Integer recipeId) {
        var recipeOptional = recipeRepository.findById(recipeId);
        if(recipeOptional.isEmpty())
            throw new DataNotFoundException(Constants.RECIPE_OR_INGREDIENT_NOT_FOUND);

        return nutritionalValueRepository.getRecipeNutritionalValues(recipeId);
    }
}
