package unibuc.RecipeManagement.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;
import org.springframework.http.HttpStatus;
import unibuc.RecipeManagement.dto.IngredientDto;
import unibuc.RecipeManagement.dto.NutritionalValueDto;
import unibuc.RecipeManagement.dto.RecipeNutritionalValuesDto;
import unibuc.RecipeManagement.entity.Recipe;
import unibuc.RecipeManagement.service.abstractions.NutritionalValueService;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class NutritionalValueControllerTests {
    @Mock
    private NutritionalValueService nutritionalValueService;

    @InjectMocks
    private NutritionalValueController nutritionalValueController;

    @Test
    public void add()
    {
        NutritionalValueDto nutritionalValueDtoTest = new NutritionalValueDto(1,20,5,10,2,6);
        when(nutritionalValueService.save(any(NutritionalValueDto.class))).thenReturn(nutritionalValueDtoTest);

        var response = nutritionalValueController.Add(nutritionalValueDtoTest);

        verify(nutritionalValueService).save(nutritionalValueDtoTest);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isEqualTo(nutritionalValueDtoTest);
    }

    @Test
    public void getRecipeNutritionalValues()
    {
        ProjectionFactory factory = new SpelAwareProxyProjectionFactory();
        RecipeNutritionalValuesDto resultDto = factory.createProjection(RecipeNutritionalValuesDto.class);
        resultDto.setFat(10);
        resultDto.setFiber(1);
        resultDto.setCalories(100);
        resultDto.setRecipeName("test");
        resultDto.setProtein(2);
        resultDto.setCarbohydrates(1);
        when(nutritionalValueService.getRecipeNutritionalValues(any(Integer.class))).thenReturn(resultDto);

        var response = nutritionalValueController.GetRecipeNutritionalValues(1);

        verify(nutritionalValueService).getRecipeNutritionalValues(any(Integer.class));
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(resultDto);
    }
}
