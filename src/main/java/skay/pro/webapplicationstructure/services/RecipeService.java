package skay.pro.webapplicationstructure.services;

import skay.pro.webapplicationstructure.model.Recipe;

public interface RecipeService {
    Recipe appendRecipe(Recipe recipe);

    Recipe getRecipe(Integer id);
}
