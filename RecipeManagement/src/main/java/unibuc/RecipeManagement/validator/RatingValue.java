package unibuc.RecipeManagement.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import unibuc.RecipeManagement.constants.Constants;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {RatingValueValidation.class})
public @interface RatingValue {
    String message() default Constants.VALUE_OUT_OF_RANGE;

    Class<?>[] groups() default {};

    Class< ? extends Payload> [] payload() default  {};
}
