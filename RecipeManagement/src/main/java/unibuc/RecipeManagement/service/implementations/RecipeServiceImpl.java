package unibuc.RecipeManagement.service.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unibuc.RecipeManagement.dto.RecipeDto;
import unibuc.RecipeManagement.mapper.RecipeMapper;
import unibuc.RecipeManagement.repository.RecipeRepository;
import unibuc.RecipeManagement.service.abstractions.RecipeService;

@Service
public class RecipeServiceImpl implements RecipeService {
    @Autowired
    private RecipeRepository recipeRepository;

    @Override
    public RecipeDto addRecipe(RecipeDto recipeDto) {
        return RecipeMapper.convertToDto(recipeRepository.save(RecipeMapper.convertToRecipe(recipeDto)));
    }
}
