package unibuc.RecipeManagement.exception;

public class DuplicateRecipeTagException extends RuntimeException{
    public DuplicateRecipeTagException(String message){
        super(message);
    }
}
