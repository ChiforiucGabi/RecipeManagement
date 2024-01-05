package unibuc.RecipeManagement.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class OnlyLettersValidation implements ConstraintValidator<OnlyLetters, String> {

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(s == null)
            return false;
        return s.matches("^[a-zA-Z ]*$");
    }
}
