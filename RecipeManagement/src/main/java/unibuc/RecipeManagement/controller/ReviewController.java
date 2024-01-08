package unibuc.RecipeManagement.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unibuc.RecipeManagement.dto.ReviewDto;
import unibuc.RecipeManagement.service.abstractions.ReviewService;

@RestController
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ReviewDto> Add(@RequestBody @Valid ReviewDto reviewDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(reviewService.save(reviewDto));
    }

}
