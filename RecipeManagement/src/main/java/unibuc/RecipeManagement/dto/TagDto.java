package unibuc.RecipeManagement.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class TagDto {
    private Integer id;
    private String name;
}
