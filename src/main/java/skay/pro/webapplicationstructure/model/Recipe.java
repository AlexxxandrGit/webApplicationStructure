package skay.pro.webapplicationstructure.model;

import lombok.*;

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
@Getter
@EqualsAndHashCode
@ToString
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
