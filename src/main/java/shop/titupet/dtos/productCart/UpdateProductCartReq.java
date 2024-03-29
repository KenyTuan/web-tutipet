package shop.titupet.dtos.productCart;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateProductCartReq {
    @NotNull(message = "Id is required!")
    private Long id;

    @NotNull(message = "Product id is required!")
    private Long productId;

    @Positive(message = "The number of products is non-negative!")
    @Max(value = 100, message = "The number of products is maximum!")
    private int quantity;

}
