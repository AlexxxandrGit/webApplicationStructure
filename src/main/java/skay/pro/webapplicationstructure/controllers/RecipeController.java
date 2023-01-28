package skay.pro.webapplicationstructure.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import skay.pro.webapplicationstructure.model.Ingredient;
import skay.pro.webapplicationstructure.model.Recipe;
import skay.pro.webapplicationstructure.services.RecipeService;

import java.util.Collection;

@RestController
@RequestMapping("/recipe")
@RequiredArgsConstructor
public class RecipeController {
    private final RecipeService recipeService;

    @GetMapping("{id}")
    Recipe getRecipe(@PathVariable Integer id) {
        return recipeService.getRecipe(id);
    }

    @GetMapping
    public Collection <Recipe> getAll() {
        return recipeService.getAll();
    }

    @DeleteMapping("{id}")
    Recipe delRecipe(@PathVariable Integer id) {
        return recipeService.remuveRecipe(id);
    }

    @PostMapping()
    Recipe appendRecipe(@RequestBody Recipe recipe) {
        return recipeService.appendRecipe(recipe);
    }

    @PutMapping("{id}")
    Recipe updateRecipe(@PathVariable Integer id, @RequestBody Recipe recipe) {
        return recipeService.updateRecipe(id, recipe);
    }

}

