package unibuc.RecipeManagement.mapper;

import unibuc.RecipeManagement.dto.IngredientDto;
import unibuc.RecipeManagement.dto.TagDto;
import unibuc.RecipeManagement.entity.Ingredient;
import unibuc.RecipeManagement.entity.Tag;

public class TagMapper {
    public static TagDto convertToDto(Tag t){
        return TagDto.builder()
                .id(t.getId())
                .name(t.getName())
                .build();
    }

    public static Tag convertToTag (TagDto tagDto){
        return Tag.builder()
                .id(tagDto.getId())
                .name(tagDto.getName())
                .build();
    }
}
