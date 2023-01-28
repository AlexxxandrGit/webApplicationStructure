package skay.pro.webapplicationstructure.services;

import org.springframework.stereotype.Service;
import skay.pro.webapplicationstructure.model.Ingredient;
import skay.pro.webapplicationstructure.model.Recipe;

import java.util.Collection;

public interface IngredientService {
    Ingredient appendIngredient(Ingredient ingredient);

    Ingredient getIngredientsId(Integer id);

    Ingredient updateIngredient(int id, Ingredient ingredient);

    Collection<Ingredient> getAll();

    Ingredient remuvIngredient(int id);

}

