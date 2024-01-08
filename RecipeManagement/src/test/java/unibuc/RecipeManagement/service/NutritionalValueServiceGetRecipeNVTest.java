package unibuc.RecipeManagement.service;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;
import unibuc.RecipeManagement.dto.RecipeNutritionalValuesDto;
import unibuc.RecipeManagement.entity.Recipe;
import unibuc.RecipeManagement.repository.NutritionalValueRepository;
import unibuc.RecipeManagement.repository.RecipeRepository;
import unibuc.RecipeManagement.service.implementations.NutritionalValueServiceImpl;

import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class NutritionalValueServiceGetRecipeNVTest {

    @Mock
    private RecipeRepository recipeRepository;

    @Mock
    private NutritionalValueRepository nutritionalValueRepository;

    @InjectMocks
    private NutritionalValueServiceImpl nutritionalValueService;

    @Test
    public void getRecipeNutritionalValue()
    {
        var recipe = new Recipe(1, "test", "test", 20, null, null, null);
        ProjectionFactory factory = new SpelAwareProxyProjectionFactory();
        RecipeNutritionalValuesDto resultDto = factory.createProjection(RecipeNutritionalValuesDto.class);
        resultDto.setFat(10);
        resultDto.setFiber(1);
        resultDto.setCalories(100);
        resultDto.setRecipeName("test");
        resultDto.setProtein(2);
        resultDto.setCarbohydrates(1);


        when(recipeRepository.findById(any(Integer.class))).thenReturn(Optional.of(recipe));
        when(nutritionalValueRepository.getRecipeNutritionalValues(any(Integer.class))).thenReturn(resultDto);

        var result = nutritionalValueService.getRecipeNutritionalValues(recipe.getId());

        verify(recipeRepository).findById(any());
        verify(nutritionalValueRepository).getRecipeNutritionalValues(any());

        assertThat(result).isEqualTo(resultDto);
    }

}
