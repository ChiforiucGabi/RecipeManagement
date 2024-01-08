package unibuc.RecipeManagement.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import unibuc.RecipeManagement.constants.Constants;
import unibuc.RecipeManagement.dto.IngredientDto;
import unibuc.RecipeManagement.dto.ReviewDto;
import unibuc.RecipeManagement.entity.Ingredient;
import unibuc.RecipeManagement.entity.Recipe;
import unibuc.RecipeManagement.entity.Review;
import unibuc.RecipeManagement.exception.DataNotFoundException;
import unibuc.RecipeManagement.mapper.IngredientMapper;
import unibuc.RecipeManagement.mapper.ReviewMapper;
import unibuc.RecipeManagement.repository.RecipeRepository;
import unibuc.RecipeManagement.repository.ReviewRepository;
import unibuc.RecipeManagement.service.implementations.ReviewServiceImpl;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class ReviewServiceSaveTest {

    @Mock
    private ReviewRepository reviewRepository;

    @Mock
    private RecipeRepository recipeRepository;

    @InjectMocks
    private ReviewServiceImpl reviewService;

    @Test
    public void add_happyPath()
    {
        ReviewDto reviewDto = new ReviewDto("good",5, 1);
        var recipe = new Recipe(1, "test","test", 10, null, null, null);

        Review savedReview = new Review(1, 5, "good", null);

        when(reviewRepository.save(any(Review.class))).thenReturn(savedReview);
        when(recipeRepository.findById(any())).thenReturn(Optional.of(recipe));

        //Act
        ReviewDto resultDto = reviewService.save(reviewDto);
        reviewDto.setRecipeId(null);

        //Assert
        verify(reviewRepository).save(any(Review.class));
        verify(recipeRepository).findById(any());
        assertThat(resultDto).isEqualTo(reviewDto);
    }

    @Test
    public void add_unhappyPath()
    {
        ReviewDto reviewDto = new ReviewDto("good",5, 1);
        when(recipeRepository.findById(any())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> reviewService.save(reviewDto))
                .isInstanceOf(DataNotFoundException.class)
                        .hasMessage(Constants.RECIPE_NOT_FOUND);

        verify(recipeRepository).findById(any());
    }

}
