package unibuc.RecipeManagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="NutritionalValues")
public class NutritionalValue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer calories;
    private Integer protein;
    private Integer carbohydrates;
    private Integer fat;
    private Integer fiber;

    @OneToOne(mappedBy = "values")
    private Ingredient ingredient;
}
