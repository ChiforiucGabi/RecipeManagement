package unibuc.RecipeManagement.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import unibuc.RecipeManagement.constants.Constants;
import unibuc.RecipeManagement.dto.IngredientDto;
import unibuc.RecipeManagement.dto.RecipeIngredientCountDto;
import unibuc.RecipeManagement.entity.Ingredient;
import unibuc.RecipeManagement.entity.RecipeIngredientCount;
import unibuc.RecipeManagement.exception.DataNotFoundException;
import unibuc.RecipeManagement.mapper.RecipeIngredientCountMapper;
import unibuc.RecipeManagement.repository.IngredientRepository;
import unibuc.RecipeManagement.repository.RecipeIngredientCountRepository;
import unibuc.RecipeManagement.service.implementations.RecipeIngredientCountServiceImpl;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class RecipeIngredientCountServiceSaveTest {
    @Mock
    private RecipeIngredientCountRepository recipeIngredientCountRepository;

    @Mock
    private IngredientRepository ingredientRepository;

    @InjectMocks
    private RecipeIngredientCountServiceImpl recipeIngredientCountService;

    @Test
    public void recipeIngredientCountSave_happyPath(){
        RecipeIngredientCountDto recipeIngredientCountDtoTest = new RecipeIngredientCountDto(2,1);

        var recipeIngredientCountToSave = RecipeIngredientCountMapper.convertToRecipeIngredientCount(recipeIngredientCountDtoTest);

        Ingredient ingredient = new Ingredient(1, "Milk", "cups", null);
        RecipeIngredientCount savedRecipeIngredientCount = new RecipeIngredientCount(1,2,ingredient,null);

        when(ingredientRepository.findById(any(Integer.class))).thenReturn(Optional.of(ingredient));
        when(recipeIngredientCountRepository.save(any(RecipeIngredientCount.class))).thenReturn(savedRecipeIngredientCount);

        RecipeIngredientCountDto resultDto = recipeIngredientCountService.save(recipeIngredientCountDtoTest);

        verify(ingredientRepository).findById(any(Integer.class));
        verify(recipeIngredientCountRepository).save(recipeIngredientCountToSave);
        assertThat(resultDto).isEqualTo(recipeIngredientCountDtoTest);
    }

    @Test
    public void recipeIngredientCountSave_unhappyPath_DataNouFound(){
        RecipeIngredientCountDto recipeIngredientCountDtoTest = new RecipeIngredientCountDto(2,1);

        when(ingredientRepository.findById(any(Integer.class))).thenReturn(Optional.empty());

        assertThatThrownBy(() -> recipeIngredientCountService.save(recipeIngredientCountDtoTest))
                .isInstanceOf(DataNotFoundException.class)
                .hasMessage(Constants.INGREDIENT_NOT_FOUND);

        verify(ingredientRepository).findById(any(Integer.class));
    }
}
