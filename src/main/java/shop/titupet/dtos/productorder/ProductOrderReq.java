package shop.titupet.dtos.productorder;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProductOrderReq {

    @NotBlank(message = "Product Cart is required.")
    private Long productCartId;

    @NotNull(message = "Product Id is required.")
    private Long productId;

    @Positive(message = "Quantity must greater than 0.")
    private int quantity;

}
