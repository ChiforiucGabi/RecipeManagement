package unibuc.RecipeManagement.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import unibuc.RecipeManagement.dto.IngredientDto;
import unibuc.RecipeManagement.dto.TagDto;
import unibuc.RecipeManagement.service.abstractions.IngredientService;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class IngredientControllerTests {

    @Mock
    private IngredientService ingredientService;

    @InjectMocks
    private IngredientController ingredientController;

    @Test
    public void add()
    {
        IngredientDto ingredientDtoTest = new IngredientDto("Milk","cups");
        when(ingredientService.save(any(IngredientDto.class))).thenReturn(ingredientDtoTest);

        var response = ingredientController.Add(ingredientDtoTest);

        verify(ingredientService).save(ingredientDtoTest);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isEqualTo(ingredientDtoTest);
    }
}
