package unibuc.RecipeManagement.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import unibuc.RecipeManagement.dto.ReviewDto;
import unibuc.RecipeManagement.service.abstractions.ReviewService;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class ReviewControllerTests {

    @Mock
    private ReviewService reviewService;

    @InjectMocks
    private ReviewController reviewController;

    @Test
    public void addReview()
    {
        ReviewDto reviewDto = new ReviewDto("good",5, 1);

        when(reviewService.save(any(ReviewDto.class))).thenReturn(reviewDto);

        var response = reviewController.Add(reviewDto);

        verify(reviewService).save(reviewDto);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isEqualTo(reviewDto);
    }
}
