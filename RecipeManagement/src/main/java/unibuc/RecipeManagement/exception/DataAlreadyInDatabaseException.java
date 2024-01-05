package unibuc.RecipeManagement.exception;

public class DataAlreadyInDatabaseException extends RuntimeException{

    public DataAlreadyInDatabaseException(String message)
    {
        super(message);
    }
}
