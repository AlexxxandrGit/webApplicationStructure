package skay.pro.webapplicationstructure.services.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import skay.pro.webapplicationstructure.model.Ingredient;
import skay.pro.webapplicationstructure.model.Recipe;
import skay.pro.webapplicationstructure.services.IngredientService;

import java.util.*;

@Service
public class IngredientServiceImpl implements IngredientService {
    private final Map<Integer, Ingredient> ingredientMap = new HashMap<>();
    private static Integer id = 0;

    @Override
    public Ingredient appendIngredient(Ingredient ingredient) {
        ingredientMap.put(id++, ingredient);
        return ingredient;
    }

    @Override
    public Ingredient getIngredientsId(Integer id) {
        return ingredientMap.get(id);
    }

    @Override
    public Ingredient updateIngredient(int id, Ingredient ingredient) {
        if (!ingredientMap.containsKey(id))
            throw new RuntimeException("Иигредиент с заданным ID не найден");
        ingredientMap.put(id, ingredient);
        return ingredient;
    }

    @Override
    public Collection<Ingredient> getAll() {
        return ingredientMap.values();
    }

    @Override
    public Ingredient remuvIngredient(int id) {
        return ingredientMap.remove(id);
    }

}
