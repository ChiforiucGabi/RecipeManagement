package unibuc.RecipeManagement.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class RatingValueValidation implements ConstraintValidator<RatingValue, Integer> {

    @Override
    public boolean isValid(Integer i, ConstraintValidatorContext constraintValidatorContext) {
        if(i == null)
            return false;

        return i >= 1 && i <= 10;
    }
}
