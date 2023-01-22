package skay.pro.webapplicationstructure.services.impl;

import org.springframework.stereotype.Service;
import skay.pro.webapplicationstructure.model.Recipe;
import skay.pro.webapplicationstructure.services.RecipeService;

import java.util.HashMap;
import java.util.Map;

@Service
public class RecipeServiceImpl implements RecipeService {
    private final Map<Integer, Recipe> recipeMap = new HashMap<>();
    private static Integer id = 0;


    @Override
    public Recipe appendRecipe(Recipe recipe) {
        recipeMap.put(id++, recipe);
        return recipe;
    }

    @Override
    public Recipe getRecipe(Integer id) {
        return recipeMap.get(id);
    }
}
