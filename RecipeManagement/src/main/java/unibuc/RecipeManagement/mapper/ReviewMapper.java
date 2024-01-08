package unibuc.RecipeManagement.mapper;

import unibuc.RecipeManagement.dto.ReviewDto;
import unibuc.RecipeManagement.entity.Review;

public class ReviewMapper {
    public static ReviewDto convertToDto(Review review){
        return ReviewDto.builder()
                .comment(review.getComment())
                .rating(review.getRating())
                .build();
    }

    public static Review convertToReview (ReviewDto reviewDto){
        return Review.builder()
                .comment(reviewDto.getComment())
                .rating(reviewDto.getRating())
                .build();
    }
}
