package unibuc.RecipeManagement.mapper;

import unibuc.RecipeManagement.dto.NutritionalValueDto;
import unibuc.RecipeManagement.entity.NutritionalValue;

public class NutritionalValueMapper {
    public static NutritionalValueDto convertToDto(NutritionalValue nutritionalValue){
        return NutritionalValueDto.builder()
                .calories(nutritionalValue.getCalories())
                .fat(nutritionalValue.getFat())
                .fiber(nutritionalValue.getFiber())
                .protein(nutritionalValue.getProtein())
                .carbohydrates(nutritionalValue.getCarbohydrates())
                .build();
    }

    public static NutritionalValue convertToNutritionalValue(NutritionalValueDto nutritionalValueDto){
        return NutritionalValue.builder()
                .calories(nutritionalValueDto.getCalories())
                .fat(nutritionalValueDto.getFat())
                .fiber(nutritionalValueDto.getFiber())
                .protein(nutritionalValueDto.getProtein())
                .carbohydrates(nutritionalValueDto.getCarbohydrates())
                .build();
    }
}
