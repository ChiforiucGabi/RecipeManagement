package unibuc.RecipeManagement.service.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import unibuc.RecipeManagement.constants.Constants;
import unibuc.RecipeManagement.dto.RecipeDisplayDto;
import unibuc.RecipeManagement.dto.RecipeDto;
import unibuc.RecipeManagement.dto.TagRecipeDto;
import unibuc.RecipeManagement.entity.Recipe;
import unibuc.RecipeManagement.entity.RecipeIngredientCount;
import unibuc.RecipeManagement.exception.DataNotFoundException;
import unibuc.RecipeManagement.exception.DuplicateRecipeTagException;
import unibuc.RecipeManagement.mapper.RecipeMapper;
import unibuc.RecipeManagement.mapper.TagMapper;
import unibuc.RecipeManagement.repository.IngredientRepository;
import unibuc.RecipeManagement.repository.RecipeIngredientCountRepository;
import unibuc.RecipeManagement.repository.RecipeRepository;
import unibuc.RecipeManagement.repository.TagRepository;
import unibuc.RecipeManagement.service.abstractions.RecipeService;

import java.util.List;

@Service
public class RecipeServiceImpl implements RecipeService {
    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private RecipeIngredientCountRepository recipeIngredientCountRepository;

    @Autowired
    private TagRepository tagRepository;

    @Override
    public RecipeDto addRecipe(RecipeDto recipeDto) {

        var recipe = recipeRepository.save(RecipeMapper.convertToRecipe(recipeDto));

        recipeDto.getIngredients().forEach(ic ->
        {
            var ingredient = ingredientRepository.findById(ic.getIngredientId());
            if(ingredient.isEmpty())
                throw new DataNotFoundException(Constants.INGREDIENT_NOT_FOUND);

            var ric = new RecipeIngredientCount(0, ic.getCount(), ingredient.get(), recipe);
            recipeIngredientCountRepository.save(ric);
        });

        return RecipeMapper.convertToDto(recipeRepository.save(recipe));
    }

    @Override
    public RecipeDisplayDto getRecipeById(Integer recipeId) {
        var recipeOptional = recipeRepository.findById(recipeId);
        if(recipeOptional.isEmpty())
            throw new DataNotFoundException(Constants.RECIPE_OR_INGREDIENT_NOT_FOUND);

        var recipeDto = RecipeMapper.convertToDisplayDto(recipeOptional.get());
        recipeDto.setIngredients(recipeRepository.getRecipeIngredientsById(recipeId));
        recipeDto.setTags(tagRepository.
                getRecipeTags(recipeId)
                .stream()
                .map(TagMapper::convertToDto)
                .toList());
        return recipeDto;
    }

    @Override
    public RecipeDto addTagsToRecipe(TagRecipeDto dto) {
        var recipeOptional = recipeRepository.findById(dto.getRecipeId());
        if(recipeOptional.isEmpty())
            throw new DataNotFoundException(Constants.RECIPE_OR_INGREDIENT_NOT_FOUND);

        var recipe = recipeOptional.get();

        try{
            for (Integer tagId : dto.getTagIds()) {
                var tag = tagRepository.findById(tagId);
                if(tag.isEmpty())
                    throw new DataNotFoundException(Constants.TAG_NOT_FOUND);

                recipeRepository.addTag( dto.getRecipeId(), tagId);
            }
            return RecipeMapper.convertToDto(recipe);
        }
        catch (DataIntegrityViolationException e)
        {
            throw new DuplicateRecipeTagException(Constants.DUPLICATE_RECIPE_TAG);
        }
    }

    @Override
    public List<RecipeDto> getRecipesByTagName(String tagName) {
        var tag = tagRepository.getTagByName(tagName);
        if(tag == null)
            throw new DataNotFoundException(Constants.TAG_NAME_DOES_NOT_EXIST);

        List<Recipe> recipeList = recipeRepository.getRecipeByTagId(tag.getId());

        return recipeList.stream().map(RecipeMapper::convertToDto).toList();
    }

    @Override
    public RecipeDto editRecipe(RecipeDto dto) {
        var recipeOptional = recipeRepository.findById(dto.getId());
        if(recipeOptional.isEmpty())
            throw new DataNotFoundException(Constants.RECIPE_NOT_FOUND);

        var recipe = recipeOptional.get();

        recipe.setName(dto.getName());
        recipe.setDescription(dto.getDescription());
        recipe.setCookingTime(dto.getCookingTime());

        if(dto.getIngredients() != null)
            dto.getIngredients().forEach(ic ->
            {
                var ingredient = ingredientRepository.findById(ic.getIngredientId());
                if(ingredient.isEmpty())
                    throw new DataNotFoundException(Constants.INGREDIENT_NOT_FOUND);

                var recipeIngredientCount = recipeIngredientCountRepository
                        .getRecipeIngredientCountByRecipeId(recipe.getId(), ingredient.get().getId());
                if(recipeIngredientCount == null)
                {
                    var ric = new RecipeIngredientCount(0, ic.getCount(), ingredient.get(), recipe);
                    recipeIngredientCountRepository.save(ric);
                }
                else
                {
                    recipeIngredientCount.setCount(ic.getCount());
                    recipeIngredientCountRepository.save(recipeIngredientCount);
                }
            });

        return RecipeMapper.convertToDto(recipeRepository.save(recipe));
    }
}
