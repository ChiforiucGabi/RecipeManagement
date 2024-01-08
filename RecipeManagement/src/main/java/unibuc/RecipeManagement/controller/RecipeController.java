package unibuc.RecipeManagement.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unibuc.RecipeManagement.dto.RecipeDisplayDto;
import unibuc.RecipeManagement.dto.RecipeDto;
import unibuc.RecipeManagement.dto.TagRecipeDto;
import unibuc.RecipeManagement.service.abstractions.RecipeService;

import java.util.List;

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

    @GetMapping("/getRecipe")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<RecipeDisplayDto> GetRecipe(@RequestParam Integer recipeId){
        return ResponseEntity.status(HttpStatus.OK).body(recipeService.getRecipeById(recipeId));
    }

    @PostMapping("/addTagToRecipe")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<RecipeDto> AddTagToRecipe(@RequestBody TagRecipeDto dto){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(recipeService.addTagsToRecipe(dto));
    }
    @GetMapping("/getRecipesByTagName")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<RecipeDto>> GetRecipesByTagName(@RequestParam String tagName){
        return ResponseEntity.status(HttpStatus.OK).body(recipeService.getRecipesByTagName(tagName));
    }

    @PutMapping("/edit")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<RecipeDto> EditRecipe(@RequestBody @Valid RecipeDto recipeDto){
        return ResponseEntity.status(HttpStatus.OK).body(recipeService.editRecipe(recipeDto));
    }
}
