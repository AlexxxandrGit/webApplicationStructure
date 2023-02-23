package skay.pro.webapplicationstructure.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import skay.pro.webapplicationstructure.model.Ingredient;
import skay.pro.webapplicationstructure.model.Recipe;
import skay.pro.webapplicationstructure.services.RecipeService;

import java.awt.*;
import java.util.Collection;

@RestController
@RequestMapping("/recipe")
@RequiredArgsConstructor
@Tag(name = "Рецепты", description = "CRUD операции для работы с рецептами")

public class RecipeController {
    private final RecipeService recipeService;

    @GetMapping("{id}")
    @Operation(description = "Получение рецепта по id")
    Recipe getRecipe(@PathVariable Integer id) {
        return recipeService.getRecipe(id);
    }

    @GetMapping
    @Operation(description = "Получение всего всей коллекции рецептов")
    public Collection<Recipe> getAll() {
        return recipeService.getAll();
    }

    @DeleteMapping("{id}")
    @Operation(description = "Удаление рецепьа по id")
    Recipe delRecipe(@PathVariable Integer id) {
        return recipeService.remuveRecipe(id);
    }

    @PostMapping()
    @Operation(description = "Добавление нового рецепта в коллекцию")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Добавление нового рецепта произошло успешно", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Integer.class))
            })
    })

    @Parameters(value = {
            @Parameter(name = "id", example = "1")
    })
    Recipe appendRecipe(@RequestBody Recipe recipe) {
        return recipeService.appendRecipe(recipe);
    }

    @PutMapping("{id}")
    @Operation(description = "Обновление рецепта ")
    Recipe updateRecipe(@PathVariable Integer id, @RequestBody Recipe recipe) {
        return recipeService.updateRecipe(id, recipe);
    }
}

