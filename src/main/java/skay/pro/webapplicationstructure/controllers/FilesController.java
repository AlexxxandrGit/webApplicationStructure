package skay.pro.webapplicationstructure.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import skay.pro.webapplicationstructure.services.FileService;
import skay.pro.webapplicationstructure.services.RecipeService;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;

@RestController
@RequestMapping("/files")
@Tag(name = "Files", description = "CRUD операции для работы с файлами")

public class FilesController {
    private final FileService recipeFileService;
    private final FileService ingredientFileService;
    private final RecipeService recipeService;

    public FilesController(@Qualifier("recipeFileService") FileService recipeFileService, @Qualifier("ingredientFileService")
    FileService ingredientFileService,RecipeService recipeService) {
        this.recipeFileService = recipeFileService;
        this.ingredientFileService = ingredientFileService;
        this.recipeService = recipeService;
    }

    @GetMapping("/ingredient/export")
    @Operation(description = "экспорт файла ингредиентов")
    public ResponseEntity<InputStreamResource> downLoadIngredientFile() throws IOException {
        InputStreamResource inputStreamResource = ingredientFileService.exportFiles(recipeService.getRecipeMap());
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .contentLength(Files.size(ingredientFileService.getPath()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename =\"Ingredients.json\"")
                .body(inputStreamResource);

    }

    @PostMapping(value = "/ingredient/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(description = "импорт файлов ингредиентов")
    public ResponseEntity<Void> uploadDataFileIngredient(@RequestParam MultipartFile file) throws FileNotFoundException {
        ingredientFileService.importFile(file);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/recipe/export")
    @Operation(description = "Экспорт файла рецептов")
    public ResponseEntity<InputStreamResource> downloadTxtRecipeFile() throws IOException {
        InputStreamResource inputStreamResource = recipeFileService.exportFiles(recipeService.getRecipeMap());
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .contentLength(Files.size(ingredientFileService.getPath()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename =\"Recipes.json\"")
                .body(inputStreamResource);
    }

    @PostMapping(value = "/recipe/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(description = "импорт файлов рецептов")
    public ResponseEntity<Void> uploadDataFileRecipe(@RequestParam MultipartFile file) throws FileNotFoundException {
        recipeFileService.importFile(file);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/recipe/exporttxt")
    @Operation(description = "Экспорт файла рецептов")
    public ResponseEntity<InputStreamResource> downloadRecipeFile() throws IOException {
        InputStreamResource inputStreamResource = recipeFileService.exportFiles(recipeService.getRecipeMap());
        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_PLAIN)
                .contentLength(Files.size(recipeFileService.getPath()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename =\"AllRecipes.txt\"")
                .body(inputStreamResource);
    }


}
