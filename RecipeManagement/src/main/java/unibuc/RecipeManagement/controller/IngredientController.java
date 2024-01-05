package unibuc.RecipeManagement.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unibuc.RecipeManagement.dto.IngredientDto;
import unibuc.RecipeManagement.service.abstractions.IngredientService;

@RestController
@RequestMapping("/ingredients")
public class IngredientController {
    @Autowired
    private IngredientService ingredientsService;


    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<IngredientDto> Add(@Valid @RequestBody IngredientDto dto)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(ingredientsService.save(dto));
    }

}
