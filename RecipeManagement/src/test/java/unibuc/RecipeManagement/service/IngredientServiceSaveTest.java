package unibuc.RecipeManagement.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import unibuc.RecipeManagement.constants.Constants;
import unibuc.RecipeManagement.dto.IngredientDto;
import unibuc.RecipeManagement.entity.Ingredient;
import unibuc.RecipeManagement.exception.DataAlreadyInDatabaseException;
import unibuc.RecipeManagement.mapper.IngredientMapper;
import unibuc.RecipeManagement.repository.IngredientRepository;
import unibuc.RecipeManagement.service.implementations.IngredientServiceImpl;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class IngredientServiceSaveTest {

    @Mock
    private IngredientRepository ingredientRepository;

    @InjectMocks
    private IngredientServiceImpl ingredientService;


    @Test
    public void ingredientSave_happyPath()
    {
        //Arrange
        IngredientDto ingredientDtoTest = new IngredientDto("Milk","cups");
        var ingredientToSave = IngredientMapper.convertToIngredient(ingredientDtoTest);

        Ingredient savedIngredient = new Ingredient(1, "Milk", "cups", null);

        when(ingredientRepository.save(any(Ingredient.class))).thenReturn(savedIngredient);

        //Act
        IngredientDto resultDto = ingredientService.save(ingredientDtoTest);

        //Assert
        verify(ingredientRepository).save(ingredientToSave);
        assertThat(resultDto).isEqualTo(ingredientDtoTest);
    }

    @Test
    public void ingredientSave_unhappyPath_ingredientAlreadyExists()
    {
        IngredientDto ingredientDtoTest = new IngredientDto("Milk","cups");
        Ingredient ingredient = new Ingredient(1, "Milk", "cups", null);

        when(ingredientRepository.getIngredientByName("Milk")).thenReturn(ingredient);

        assertThatThrownBy(() -> ingredientService.save(ingredientDtoTest))
                .isInstanceOf(DataAlreadyInDatabaseException.class)
                        .hasMessage(Constants.INGREDIENT_ALREADY_EXISTS);
        verify(ingredientRepository).getIngredientByName("Milk");
    }
}
