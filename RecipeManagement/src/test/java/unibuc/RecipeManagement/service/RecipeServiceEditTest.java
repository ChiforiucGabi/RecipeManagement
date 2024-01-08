package unibuc.RecipeManagement.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import unibuc.RecipeManagement.constants.Constants;
import unibuc.RecipeManagement.dto.RecipeDto;
import unibuc.RecipeManagement.dto.RecipeIngredientCountDto;
import unibuc.RecipeManagement.entity.Ingredient;
import unibuc.RecipeManagement.entity.Recipe;
import unibuc.RecipeManagement.exception.DataNotFoundException;
import unibuc.RecipeManagement.mapper.RecipeMapper;
import unibuc.RecipeManagement.repository.IngredientRepository;
import unibuc.RecipeManagement.repository.RecipeIngredientCountRepository;
import unibuc.RecipeManagement.repository.RecipeRepository;
import unibuc.RecipeManagement.service.implementations.RecipeServiceImpl;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class RecipeServiceEditTest {
    @Mock
    private RecipeRepository recipeRepository;

    @Mock
    private IngredientRepository ingredientRepository;

    @Mock
    private RecipeIngredientCountRepository recipeIngredientCountRepository;

    @InjectMocks
    private RecipeServiceImpl recipeService;

    @Test
    public void editRecipeTest_happyPath()
    {
        RecipeIngredientCountDto dto = new RecipeIngredientCountDto(1, 1) ;
        RecipeDto recipeDto = new RecipeDto(1, "test", "test", 10, List.of(dto));
        var ingredient = new Ingredient(1, "testing", "buc", null);

        var recipe = new Recipe(1, "beforeEdit","test", 10, null, null, null);

        when(recipeRepository.save(any(Recipe.class))).thenReturn(recipe);
        when(recipeRepository.findById(any(Integer.class))).thenReturn(Optional.of(recipe));
        when(ingredientRepository.findById(any())).thenReturn(Optional.of(ingredient));

        var result = recipeService.editRecipe(recipeDto);
        recipeDto.setIngredients(null);

        assertThat(result).isEqualTo(recipeDto);
        assertThat(result.getName()).isEqualTo(recipeDto.getName());
        verify(recipeRepository).save(any(Recipe.class));
        verify(ingredientRepository).findById(any());
    }

    @Test
    public void editRecipeTest_unhappyPath()
    {
        RecipeIngredientCountDto dto = new RecipeIngredientCountDto(1, 1) ;
        RecipeDto recipeDto = new RecipeDto(1, "test", "test", 10, List.of(dto));

        when(recipeRepository.findById(any(Integer.class))).thenReturn(Optional.empty());

        assertThatThrownBy(() -> recipeService.editRecipe(recipeDto))
                .isInstanceOf(DataNotFoundException.class)
                        .hasMessage(Constants.RECIPE_NOT_FOUND);
        verify(recipeRepository).findById(any());
    }

}
