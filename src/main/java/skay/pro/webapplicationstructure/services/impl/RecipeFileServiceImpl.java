package skay.pro.webapplicationstructure.services.impl;

import exception.FileProcessingException;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import skay.pro.webapplicationstructure.model.Recipe;
import skay.pro.webapplicationstructure.services.FileService;

import javax.annotation.PostConstruct;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Map;

@Service("recipeFileService")

public class RecipeFileServiceImpl implements FileService {

    @Value("${path.to.files}")
    private String dataFilePathRecipe;
    @Value("${name.of.recipe.file}")
    private String dataFileNameRecipe;

    private Path path;


    @PostConstruct
    private void init() {
        path = Path.of(dataFilePathRecipe, dataFileNameRecipe);
    }


    @Override
    public boolean saveToFile(String json) {
        try {
            cleanDataFile();
            Files.writeString(Path.of(dataFilePathRecipe, dataFileNameRecipe), json);
            return true;
        } catch (IOException e) {
            return false;
        }

    }

    @Override
    public String readFromFile() {
        if (Files.exists(Path.of(dataFilePathRecipe, dataFileNameRecipe))) {
            try {
                return Files.readString(Path.of(dataFilePathRecipe, dataFileNameRecipe));
            } catch (IOException e) {
                throw new FileProcessingException("не удалось прочитать файл");
            }
        }
        return "{ }";
    }


    @Override
    public boolean cleanDataFile() {
        try {
            Path path = Path.of(dataFilePathRecipe, dataFileNameRecipe);
            Files.deleteIfExists(path);
            Files.createFile(path);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public File getDataFile() {
        return new File(dataFilePathRecipe + "/" + dataFileNameRecipe);

    }

    @Override
    public InputStreamResource exportFiles(Map<Integer, Recipe> recipeMap) throws FileNotFoundException {
        File file = getDataFile();
        return new InputStreamResource(new FileInputStream(file));
    }

    @Override
    public void importFile(MultipartFile file) throws FileNotFoundException {
        cleanDataFile();
        FileOutputStream fos = new FileOutputStream(getDataFile());
        try {
            IOUtils.copy(file.getInputStream(), fos);
        } catch (IOException e) {
            throw new FileNotFoundException("проблема сохранения файла");

        }
    }

    @Override
    public InputStreamResource exportTxtFile(Map<Integer, Recipe> recipeMap) throws FileNotFoundException, IOException {
        Path path = this.createAllRecipesFile("allRecipes");
        for (Recipe recipe : recipeMap.values()) {
            try (BufferedWriter writer = Files.newBufferedWriter(path, StandardOpenOption.APPEND)) {
                writer.append(" Название рецепта: ");
                writer.append(recipe.getName());
                writer.append("\n Время приготовления: ");
                writer.append(String.valueOf(recipe.getTimeForPreparing()));
                writer.append(" ");
                writer.append("\n Ингредиенты: ");
                writer.append(String.valueOf(recipe.getIngredients()));
                writer.append("\n Шаги приготовления: ");
                writer.append(String.valueOf(recipe.getCookingSteps()));
            }
        }

        File file = path.toFile();
        return new InputStreamResource(new FileInputStream(file));
    }

    private Path createAllRecipesFile(String suffix) throws IOException {
        if (Files.exists(Path.of(dataFilePathRecipe, suffix))) {
            Files.delete(Path.of(dataFilePathRecipe, suffix));
            Files.createFile(Path.of(dataFilePathRecipe, suffix));
            return Path.of(dataFilePathRecipe, suffix);
        }
        return Files.createFile(Path.of(dataFilePathRecipe, suffix));
    }

    @Override
    public Path getPath() {
        return path;
    }


}
