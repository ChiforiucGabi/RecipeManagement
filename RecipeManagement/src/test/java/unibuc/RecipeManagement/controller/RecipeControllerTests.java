package unibuc.RecipeManagement.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import unibuc.RecipeManagement.dto.IngredientDto;
import unibuc.RecipeManagement.dto.RecipeDisplayDto;
import unibuc.RecipeManagement.dto.RecipeDto;
import unibuc.RecipeManagement.entity.Recipe;
import unibuc.RecipeManagement.service.abstractions.RecipeService;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class RecipeControllerTests {
    @Mock
    private RecipeService recipeService;

    @InjectMocks
    private RecipeController recipeController;

    @Test
    public void add()
    {
        RecipeDto recipeDto = new RecipeDto(0, "test", "test", 10, null);
        when(recipeService.addRecipe(any(RecipeDto.class))).thenReturn(recipeDto);

        var response = recipeController.AddRecipe(recipeDto);

        verify(recipeService).addRecipe(recipeDto);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isEqualTo(recipeDto);
    }

    @Test
    public void getRecipeByTagName()
    {
        var recipeDtoList = List.of(new RecipeDto(1, "test", "test", 10, null));

        when(recipeService.getRecipesByTagName(any(String.class))).thenReturn(recipeDtoList);

        var resultDto = recipeController.GetRecipesByTagName("test");

        assertThat(resultDto.getBody()).isEqualTo(recipeDtoList);
        assertThat(resultDto.getStatusCode()).isEqualTo(HttpStatus.OK);
        verify(recipeService).getRecipesByTagName(any(String.class));
    }

    @Test
    public void getRecipeById()
    {
        RecipeDisplayDto recipeDto = new RecipeDisplayDto("test", 10, "testdesc", null, null);

        when(recipeService.getRecipeById(any(Integer.class))).thenReturn(recipeDto);

        var resultDto = recipeController.GetRecipe(1);

        assertThat(resultDto.getBody()).isEqualTo(recipeDto);
        assertThat(resultDto.getStatusCode()).isEqualTo(HttpStatus.OK);
        verify(recipeService).getRecipeById(any(Integer.class));
    }
    @Test
    public void edit()
    {
        RecipeDto recipeDto = new RecipeDto(1, "test", "test", 10, null);
        when(recipeService.editRecipe(any(RecipeDto.class))).thenReturn(recipeDto);

        var response = recipeController.EditRecipe(recipeDto);

        verify(recipeService).editRecipe(recipeDto);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(recipeDto);
    }

}
