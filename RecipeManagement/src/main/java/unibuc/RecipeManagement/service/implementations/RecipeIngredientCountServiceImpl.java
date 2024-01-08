package unibuc.RecipeManagement.service.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unibuc.RecipeManagement.constants.Constants;
import unibuc.RecipeManagement.dto.NutritionalValueDto;
import unibuc.RecipeManagement.dto.RecipeIngredientCountDto;
import unibuc.RecipeManagement.entity.RecipeIngredientCount;
import unibuc.RecipeManagement.exception.DataNotFoundException;
import unibuc.RecipeManagement.mapper.NutritionalValueMapper;
import unibuc.RecipeManagement.mapper.RecipeIngredientCountMapper;
import unibuc.RecipeManagement.repository.IngredientRepository;
import unibuc.RecipeManagement.repository.RecipeIngredientCountRepository;
import unibuc.RecipeManagement.repository.RecipeRepository;
import unibuc.RecipeManagement.service.abstractions.RecipeIngredientCountService;

@Service
public class RecipeIngredientCountServiceImpl implements RecipeIngredientCountService {
    @Autowired
    private RecipeIngredientCountRepository recipeIngredientCountRepository;

    @Autowired
    private IngredientRepository ingredientRepository;


    @Override
    public RecipeIngredientCountDto save(RecipeIngredientCountDto entity) {
        var ingredientOptional = ingredientRepository.findById(entity.getIngredientId());
        if(ingredientOptional.isEmpty())
        {
            throw new DataNotFoundException(Constants.INGREDIENT_NOT_FOUND);
        }


        return RecipeIngredientCountMapper.convertToDto(recipeIngredientCountRepository.save(RecipeIngredientCountMapper.convertToRecipeIngredientCount(entity)));

    }
}
