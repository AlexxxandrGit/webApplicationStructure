package skay.pro.webapplicationstructure.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Recipe {
    @NotBlank
    private String name;
    @Positive
    private int timeForPreparing;
    @NotEmpty
    private List<Ingredient> ingredients;
    @NotEmpty
    private List<String> cookingSteps;


}
