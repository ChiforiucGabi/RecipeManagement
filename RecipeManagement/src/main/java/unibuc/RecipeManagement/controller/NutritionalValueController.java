package unibuc.RecipeManagement.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unibuc.RecipeManagement.dto.NutritionalValueDto;
import unibuc.RecipeManagement.service.abstractions.NutritionalValueService;

@RestController
@RequestMapping("/nutritionalValues")
public class NutritionalValueController {
    @Autowired
    private NutritionalValueService nutritionalValueService;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<NutritionalValueDto> Add(@Valid @RequestBody NutritionalValueDto dto)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(nutritionalValueService.save(dto));
    }
}
