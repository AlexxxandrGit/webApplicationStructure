package skay.pro.webapplicationstructure.services.impl;

import exception.FileProcessingException;
import org.apache.tomcat.util.http.fileupload.IOUtils;
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
import java.util.Map;

@Service("ingredientFileService")

public class IngredientFileServiceImpl implements FileService {


    @Value("src/main/resources")
    private String dataFilePathIngredient;
    @Value("ingredient.json")
    private String dataFileNameIngredient;

    private Path path;

    @PostConstruct
    private void init() {
        path = Path.of(dataFilePathIngredient, dataFileNameIngredient);
    }

    @Override
    public boolean saveToFile(String json) {
        try {
            cleanDataFile();
            Files.writeString(Path.of(dataFilePathIngredient, dataFileNameIngredient), json);
            return true;
        } catch (IOException e) {
            return false;
        }

    }

    @Override
    public String readFromFile() {
        if (Files.exists(Path.of(dataFilePathIngredient, dataFileNameIngredient))) {
            try {
                return Files.readString(Path.of(dataFilePathIngredient, dataFileNameIngredient));
            } catch (IOException e) {
                throw new FileProcessingException("не удалось прочитать файл");
            }
        }
        return "{ }";
    }


    @Override
    public boolean cleanDataFile() {
        try {
            Path path = Path.of(dataFilePathIngredient, dataFileNameIngredient);
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
        return new File(dataFilePathIngredient + "/" + dataFileNameIngredient);

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
    public Path getPath() {
        return path;
    }

}