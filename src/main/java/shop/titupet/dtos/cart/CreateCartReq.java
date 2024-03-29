package shop.titupet.dtos.cart;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import shop.titupet.dtos.productCart.ProductCartReq;

@Getter @Setter
public class CreateCartReq {

    @NotNull(message = "Product required!")
    private ProductCartReq productCartReqs;
}
