package unibuc.RecipeManagement.dto;

public interface RecipeNutritionalValuesDto {
    String getRecipeName();

    Integer getCalories();

    Integer getCarbohydrates();

    Integer getFat();

    Integer getFiber();

    Integer getProtein();

    void setRecipeName(String recipeName);
    void setCalories(Integer calories);
    void setCarbohydrates(Integer carbohydrates);
    void setFat(Integer fat);
    void setFiber(Integer fiber);
    void setProtein(Integer protein);

}
