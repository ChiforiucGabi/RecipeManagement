package unibuc.RecipeManagement.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unibuc.RecipeManagement.dto.NutritionalValueDto;
import unibuc.RecipeManagement.dto.TagDto;
import unibuc.RecipeManagement.service.abstractions.NutritionalValueService;
import unibuc.RecipeManagement.service.abstractions.TagService;

@RestController
@RequestMapping("/Tags")
public class TagController {
    @Autowired
    private TagService tagService;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<TagDto> Add(@Valid @RequestBody TagDto dto)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(tagService.save(dto));
    }
}
