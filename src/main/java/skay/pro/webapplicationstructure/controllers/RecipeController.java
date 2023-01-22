package skay.pro.webapplicationstructure.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import skay.pro.webapplicationstructure.model.Recipe;
import skay.pro.webapplicationstructure.services.RecipeService;

@RestController
@RequestMapping("/recipe")
@RequiredArgsConstructor
public class RecipeController {
    private final RecipeService recipeService;

    @GetMapping("{id}")
    Recipe getRecipe(@PathVariable Integer id) {
        return recipeService.getRecipe(id);
    }

    @PostMapping()
    Recipe appendRecipe(@RequestBody Recipe recipe) {
        return recipeService.appendRecipe(recipe);
    }

}

