package skay.pro.webapplicationstructure.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Ingredient {

    @NotBlank(message = "Название не может быть пустым")
    private String name;
    @Positive
    private Integer amount;
    private String unitOfWeight;
}
