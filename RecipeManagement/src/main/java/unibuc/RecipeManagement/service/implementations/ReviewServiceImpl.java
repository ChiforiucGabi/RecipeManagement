package unibuc.RecipeManagement.service.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unibuc.RecipeManagement.constants.Constants;
import unibuc.RecipeManagement.dto.ReviewDto;
import unibuc.RecipeManagement.exception.DataNotFoundException;
import unibuc.RecipeManagement.mapper.ReviewMapper;
import unibuc.RecipeManagement.repository.RecipeRepository;
import unibuc.RecipeManagement.repository.ReviewRepository;
import unibuc.RecipeManagement.service.abstractions.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private RecipeRepository recipeRepository;


    @Override
    public ReviewDto save(ReviewDto entity) {
        var recipeOptional = recipeRepository.findById(entity.getRecipeId());
        if (recipeOptional.isEmpty())
            throw new DataNotFoundException(Constants.RECIPE_NOT_FOUND);

        var review = ReviewMapper.convertToReview(entity);
        review.setRecipe(recipeOptional.get());

        return ReviewMapper.convertToDto(reviewRepository.save(review));
    }
}
