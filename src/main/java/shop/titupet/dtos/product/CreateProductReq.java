package shop.titupet.dtos.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.service.annotation.GetExchange;
import shop.titupet.models.entities.Product;
import shop.titupet.models.entities.ProductType;
import shop.titupet.models.enums.EnableStatus;
import shop.titupet.models.enums.PetType;


@Getter @Setter
public class CreateProductReq {

    @NotBlank(message = "Name is required.")
    private String name;

    private String description;

    private String info;

    @Positive(message = "Price must greater than 0.")
    private double price;

    private String img;

    @NotNull(message = "Type is required.")
    private Integer type_id;

}
