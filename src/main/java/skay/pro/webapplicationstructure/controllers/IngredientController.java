package skay.pro.webapplicationstructure.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import skay.pro.webapplicationstructure.model.Ingredient;
import skay.pro.webapplicationstructure.services.IngredientService;

import javax.validation.Valid;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/ingredient")
@RequiredArgsConstructor
public class IngredientController {
    private final IngredientService ingredientService;

    @GetMapping("{id}")
    Ingredient getIngredient(@PathVariable Integer id) {
        return ingredientService.getIngredientsId(id);
    }

    @PostMapping()
    Ingredient appendIngredient(@Valid @RequestBody Ingredient ingredient) {
        return ingredientService.appendIngredient(ingredient);
    }

    @GetMapping
    public Collection<Ingredient> getAll() {
        return this.ingredientService.getAll();
    }

    @DeleteMapping("{id}")
    Ingredient delIngredient(@PathVariable Integer id) {
        return ingredientService.remuvIngredient(id);
    }

    @PutMapping("{id}")
    Ingredient updateIngredient(@PathVariable Integer id, @RequestBody Ingredient ingredient) {
        return ingredientService.updateIngredient(id, ingredient);
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}

