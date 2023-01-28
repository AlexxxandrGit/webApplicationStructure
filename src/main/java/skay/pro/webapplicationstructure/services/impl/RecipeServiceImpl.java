package skay.pro.webapplicationstructure.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import skay.pro.webapplicationstructure.model.Recipe;
import skay.pro.webapplicationstructure.services.IngredientService;
import skay.pro.webapplicationstructure.services.RecipeService;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class RecipeServiceImpl implements RecipeService {
    private final IngredientService ingredientService;
    private final Map<Integer, Recipe> recipeMap = new HashMap<>();
    private static Integer id = 0;


    @Override
    public Recipe appendRecipe(Recipe recipe) {
        recipeMap.put(id++, recipe);
        return recipe;
    }

    @Override
    public Recipe getRecipe(Integer id) {
        if (recipeMap.containsKey(id) && id > 0) {
            return recipeMap.get(id);
        } else {
            throw new RuntimeException(" ID с данным рецептом не существует.");
        }
    }


    @Override
    public Collection<Recipe> getAll() {
        return recipeMap.values();
    }

    @Override
    public Recipe remuveRecipe(int id) {
        return recipeMap.remove(id);
    }

    @Override
    public Recipe updateRecipe(int id, Recipe recipe) {
        if (!recipeMap.containsKey(id))
            throw new RuntimeException("Рецепт с заданным ID не найден");
        recipeMap.put(id, recipe);
        return recipe;
    }

}
