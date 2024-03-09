package shop.titupet.models.dtos.cart;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import shop.titupet.models.dtos.productCart.ProductCartReq;

@Getter @Setter
public class CreateCartReq {

    @NotNull(message = "Product required!")
    private ProductCartReq productCartReqs;
}
