package unibuc.RecipeManagement.service.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unibuc.RecipeManagement.constants.Constants;
import unibuc.RecipeManagement.dto.IngredientDto;
import unibuc.RecipeManagement.exception.DataAlreadyInDatabaseException;
import unibuc.RecipeManagement.mapper.IngredientMapper;
import unibuc.RecipeManagement.repository.IngredientRepository;
import unibuc.RecipeManagement.service.abstractions.IngredientService;

@Service
public class IngredientServiceImpl implements IngredientService {
    @Autowired
    private IngredientRepository ingredientRepository;

    @Override
    public IngredientDto save(IngredientDto entity) {

        var ingredient = ingredientRepository.getIngredientByName(entity.getName());
        if(ingredient != null)
        {
            throw new DataAlreadyInDatabaseException(Constants.INGREDIENT_ALREADY_EXISTS);
        }

        return IngredientMapper.convertToDto(ingredientRepository.save(IngredientMapper.convertToIngredient(entity)));
    }
}
