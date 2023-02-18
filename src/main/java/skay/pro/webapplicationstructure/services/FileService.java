package skay.pro.webapplicationstructure.services;

import org.springframework.core.io.InputStreamResource;
import org.springframework.web.multipart.MultipartFile;
import org.webjars.NotFoundException;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.channels.MulticastChannel;
import java.nio.file.Path;

public interface FileService {
    boolean saveToFile(String json);

    String readFromFile();

    boolean cleanDataFile();

    File getDataFile();

    InputStreamResource exportFiles() throws NotFoundException, FileNotFoundException;

    void importFile(MultipartFile file) throws NotFoundException, FileNotFoundException;

    public Path getPath();

}
