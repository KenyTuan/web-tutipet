package shop.titupet.models.dtos.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import shop.titupet.models.enums.EnableStatus;
import shop.titupet.models.enums.PetType;


@Getter
@Setter
public class UpdateProductReq {

    private Long id;

    private PetType petTypes;

    @NotBlank(message = "Name is required.")
    private String name;

    private String description;

    private String info;

    @Positive(message = "Price must greater than 0")
    @NotBlank(message = "Price is required.")
    private double price;

    private String img;

}
