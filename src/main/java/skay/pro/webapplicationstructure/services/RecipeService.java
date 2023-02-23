package skay.pro.webapplicationstructure.services;

import org.springframework.core.io.InputStreamResource;
import skay.pro.webapplicationstructure.model.Recipe;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;

public interface RecipeService {
    Recipe appendRecipe(Recipe recipe);

    Recipe getRecipe(Integer id);

    Collection<Recipe> getAll();

    Recipe remuveRecipe(int id);

    Recipe updateRecipe(int id, Recipe recipe);
    Map<Integer, Recipe> getRecipeMap();


}
