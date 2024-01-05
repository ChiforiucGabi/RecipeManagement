package unibuc.RecipeManagement.service.abstractions;

public interface GenericService<T> {
    T save(T entity);
}
