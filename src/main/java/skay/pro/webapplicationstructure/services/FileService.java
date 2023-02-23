package skay.pro.webapplicationstructure.services;

import org.springframework.core.io.InputStreamResource;
import org.springframework.web.multipart.MultipartFile;
import org.webjars.NotFoundException;
import skay.pro.webapplicationstructure.model.Recipe;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

public interface FileService {
    boolean saveToFile(String json);

    String readFromFile();

    boolean cleanDataFile();

    File getDataFile();

    InputStreamResource exportFiles(Map<Integer, Recipe> recipeMap) throws NotFoundException, FileNotFoundException;

    void importFile(MultipartFile file) throws NotFoundException, FileNotFoundException;

    public Path getPath();

    InputStreamResource exportTxtFile(Map<Integer, Recipe> recipeMap) throws FileNotFoundException, IOException;
}
