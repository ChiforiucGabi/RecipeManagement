package unibuc.RecipeManagement.service.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unibuc.RecipeManagement.constants.Constants;
import unibuc.RecipeManagement.dto.NutritionalValueDto;
import unibuc.RecipeManagement.entity.NutritionalValue;
import unibuc.RecipeManagement.exception.DataNotFoundException;
import unibuc.RecipeManagement.mapper.NutritionalValueMapper;
import unibuc.RecipeManagement.repository.IngredientRepository;
import unibuc.RecipeManagement.repository.NutritionalValueRepository;
import unibuc.RecipeManagement.service.abstractions.NutritionalValueService;

@Service
public class NutritionalValueServiceImpl implements NutritionalValueService {
    @Autowired
    private NutritionalValueRepository nutritionalValueRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    @Override
    public NutritionalValueDto save(NutritionalValueDto entity) {
        var ingredientOptional = ingredientRepository.findById(entity.getIngredientId());
        if(ingredientOptional.isEmpty())
        {
            throw new DataNotFoundException(Constants.INGREDIENT_NOT_FOUND);
        }

        var nutritionalValue = NutritionalValueMapper.convertToNutritionalValue(entity);

        nutritionalValue.setIngredient(ingredientOptional.get());
        var nutritionalValue_final = nutritionalValueRepository.save(nutritionalValue);

        var ingredient = ingredientOptional.get();
        ingredient.setValues(nutritionalValue_final);
        ingredientRepository.save(ingredient);

        return NutritionalValueMapper.convertToDto(nutritionalValue_final);

    }
}
