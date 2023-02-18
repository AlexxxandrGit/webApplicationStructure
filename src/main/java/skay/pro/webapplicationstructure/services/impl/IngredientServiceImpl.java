package skay.pro.webapplicationstructure.services.impl;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import exception.FileProcessingException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import skay.pro.webapplicationstructure.model.Ingredient;
import skay.pro.webapplicationstructure.services.FileService;
import skay.pro.webapplicationstructure.services.IngredientService;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

@Service

public class IngredientServiceImpl implements IngredientService {
    private final FileService fileService;

    public IngredientServiceImpl(@Qualifier("ingredientFileService") FileService fileService) {
        this.fileService = fileService;
    }

    private Map<Integer, Ingredient> ingredientMap = new HashMap<>();
    private static Integer id = 0;


    @Override
    public Ingredient appendIngredient(Ingredient ingredient) {
        ingredientMap.put(id++, ingredient);
        saveToFileIngredient();
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
        saveToFileIngredient();
        return ingredient;
    }

    @Override
    public Collection<Ingredient> getAll() {
        return ingredientMap.values();
    }

    @Override
    public Ingredient remuvIngredient(int id) {
        saveToFileIngredient();
        return ingredientMap.remove(id);
    }

    @PostConstruct
    public void initIngredient() throws FileProcessingException {
        readFromFileIngredient();
    }

    private void readFromFileIngredient() throws FileProcessingException {
        try {
            String json = fileService.readFromFile();
            ingredientMap = new ObjectMapper().readValue(json, new TypeReference<HashMap<Integer, Ingredient>>() {
            });
        } catch (JsonProcessingException e) {
            throw new FileProcessingException("файл не удалось прочитать");
        }

    }


    private void saveToFileIngredient() throws FileProcessingException {
        try {
            String json = new ObjectMapper().writeValueAsString(ingredientMap);
            fileService.saveToFile(json);
        } catch (JsonProcessingException e) {
            throw new FileProcessingException("Файл не удалось сохранить");
        }

    }


}
