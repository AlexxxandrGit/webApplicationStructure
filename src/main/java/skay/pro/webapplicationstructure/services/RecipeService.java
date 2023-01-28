package skay.pro.webapplicationstructure.services;

import skay.pro.webapplicationstructure.model.Recipe;

import java.util.Collection;

public interface RecipeService {
    Recipe appendRecipe(Recipe recipe);

    Recipe getRecipe(Integer id);

    Collection<Recipe> getAll();

    Recipe remuveRecipe(int id);

    Recipe updateRecipe(int id, Recipe recipe);
}
