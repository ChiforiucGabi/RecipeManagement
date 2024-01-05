package unibuc.RecipeManagement.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unibuc.RecipeManagement.dto.RecipeDto;
import unibuc.RecipeManagement.service.abstractions.RecipeService;

@RestController
@RequestMapping("/recipes")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @PostMapping("/addRecipe")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<RecipeDto> AddRecipe(@RequestBody @Valid RecipeDto recipeDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(recipeService.addRecipe(recipeDto));
    }

}
