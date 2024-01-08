package unibuc.RecipeManagement.controller;

import org.hibernate.query.SyntaxException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import unibuc.RecipeManagement.exception.DataAlreadyInDatabaseException;
import unibuc.RecipeManagement.exception.DataNotFoundException;
import unibuc.RecipeManagement.exception.DuplicateRecipeTagException;

import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionControllerAdvice {
    @ExceptionHandler({DataNotFoundException.class})
    public ResponseEntity<String> handleNotFound(Exception exception){
        System.out.println(exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<String> handleInvalidField(MethodArgumentNotValidException exception){
        System.out.println(exception.getMessage());
        String invalidFields = "Invalid fields: \n"
                + exception.getBindingResult().getFieldErrors().stream()
                .map(e -> "Field: " + e.getField() + ", error: " + e.getDefaultMessage() + ", value: " + e.getRejectedValue())
                .collect(Collectors.joining("\n"));
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                .body(invalidFields);
    }

    @ExceptionHandler({DataAlreadyInDatabaseException.class, DuplicateRecipeTagException.class})
    public ResponseEntity<String> handleDataAlreadyExists(Exception exception){
        System.out.println(exception.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(exception.getMessage());
    }
}
