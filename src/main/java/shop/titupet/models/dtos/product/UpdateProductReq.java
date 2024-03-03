package shop.titupet.models.dtos.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import shop.titupet.models.entities.ProductType;
import shop.titupet.models.enums.EnableStatus;
import shop.titupet.models.enums.PetType;


@Getter
@Setter
public class UpdateProductReq {

    @NotBlank(message = "ID is required")
    private Long id;

    @NotBlank(message = "Name is required.")
    private String name;

    @NotNull(message = "Type is required.")
    private Integer type_id;

    @Positive(message = "Price must greater than 0")
    private double price;

    private String description;

    private String info;

    private String img;

}
