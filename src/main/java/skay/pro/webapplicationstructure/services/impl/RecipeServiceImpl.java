package skay.pro.webapplicationstructure.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import exception.FileProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import skay.pro.webapplicationstructure.model.Recipe;
import skay.pro.webapplicationstructure.services.FileService;
import skay.pro.webapplicationstructure.services.RecipeService;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class RecipeServiceImpl implements RecipeService {
    private FileService fileService;

    public RecipeServiceImpl(@Qualifier("recipeFileService") FileService fileService) {
        this.fileService = fileService;
    }

    private Map<Integer, Recipe> recipeMap = new HashMap<>();
    private static Integer id = 0;


    @Override
    public Recipe appendRecipe(Recipe recipe) {
        recipeMap.put(id++, recipe);
        saveToFileRecipe();

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
        saveToFileRecipe();
        return recipeMap.remove(id);
    }

    @Override
    public Recipe updateRecipe(int id, Recipe recipe) {
        if (!recipeMap.containsKey(id))
            throw new RuntimeException("Рецепт с заданным ID не найден");
        recipeMap.put(id, recipe);
        saveToFileRecipe();
        return recipe;
    }

    @PostConstruct
    public void initRecipe() throws FileProcessingException {
        readFromFileRecipe();
    }

    private void readFromFileRecipe() throws FileProcessingException {
        try {
            String json = fileService.readFromFile();
            recipeMap = new ObjectMapper().readValue(json, new TypeReference<HashMap<Integer, Recipe>>() {
            });
        } catch (JsonProcessingException e) {
            throw new FileProcessingException("файл не удалось прочитать");
        }

    }

    private void saveToFileRecipe() throws FileProcessingException {
        try {
            String json = new ObjectMapper().writeValueAsString(recipeMap);
            fileService.saveToFile(json);
        } catch (JsonProcessingException e) {
            throw new FileProcessingException("Файл не удалось сохранить");
        }

    }

}
