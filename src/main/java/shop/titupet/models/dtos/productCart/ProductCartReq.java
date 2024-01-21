package shop.titupet.models.dtos.productCart;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProductCartReq {

    @Positive(message = "Quantity is ")
    private int quantity;

    @NotNull
    private Long productId;

    @NotNull
    private Long cartId;
}
