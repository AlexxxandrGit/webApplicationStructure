package skay.pro.webapplicationstructure.model;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode
public class Ingredient {

    @NotBlank(message = "Название не может быть пустым")
    private String name;
    @Positive
    private Integer amount;
    private String unitOfWeight;
}
