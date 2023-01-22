package skay.pro.webapplicationstructure.services;

import org.springframework.stereotype.Service;
import skay.pro.webapplicationstructure.model.Ingredient;

public interface IngredientService {
    Ingredient appendIngredient(Ingredient ingredient);

    Ingredient getIngredientsId(Integer id);


}
