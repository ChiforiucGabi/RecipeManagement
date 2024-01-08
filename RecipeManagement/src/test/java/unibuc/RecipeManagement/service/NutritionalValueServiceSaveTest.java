package unibuc.RecipeManagement.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import unibuc.RecipeManagement.constants.Constants;
import unibuc.RecipeManagement.dto.NutritionalValueDto;
import unibuc.RecipeManagement.entity.Ingredient;
import unibuc.RecipeManagement.entity.NutritionalValue;
import unibuc.RecipeManagement.exception.DataNotFoundException;
import unibuc.RecipeManagement.mapper.NutritionalValueMapper;
import unibuc.RecipeManagement.repository.IngredientRepository;
import unibuc.RecipeManagement.repository.NutritionalValueRepository;
import unibuc.RecipeManagement.service.abstractions.NutritionalValueService;
import unibuc.RecipeManagement.service.implementations.NutritionalValueServiceImpl;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class NutritionalValueServiceSaveTest {
    @Mock
    private IngredientRepository ingredientRepository;

    @Mock
    private NutritionalValueRepository nutritionalValueRepository;

    @InjectMocks
    private NutritionalValueServiceImpl nutritionalValueService;

    @Test
    public void nutritionalValueSave_happyPath(){
        NutritionalValueDto nutritionalValueDtoTest = new NutritionalValueDto(1,20,5,10,2,6);
        Ingredient ingredient = new Ingredient(1, "Milk", "cups", null);

        when(ingredientRepository.findById(any(Integer.class))).thenReturn(Optional.of(ingredient));
        when(ingredientRepository.save(any(Ingredient.class))).thenReturn(ingredient);

        NutritionalValueDto resultDto = nutritionalValueService.save(nutritionalValueDtoTest);
        resultDto.setIngredientId(ingredient.getId());

        verify(ingredientRepository).findById(any(Integer.class));
        verify(ingredientRepository).save(any(Ingredient.class));

        assertThat(resultDto).isEqualTo(nutritionalValueDtoTest);
    }

    @Test
    public void nutritionalValueSave_unhappyPath_IngredientNotFound(){
        NutritionalValueDto nutritionalValueDtoTest = new NutritionalValueDto(1,20,5,10,2,6);

        when(ingredientRepository.findById(any(Integer.class))).thenReturn(Optional.empty());

        assertThatThrownBy(() -> nutritionalValueService.save(nutritionalValueDtoTest))
                .isInstanceOf(DataNotFoundException.class)
                .hasMessage(Constants.INGREDIENT_NOT_FOUND);

        verify(ingredientRepository).findById(any(Integer.class));
    }

}
