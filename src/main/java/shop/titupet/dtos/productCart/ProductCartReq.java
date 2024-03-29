package shop.titupet.dtos.productCart;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProductCartReq {



    @NotBlank(message = "Product id is required.")
    private Long productId;

}
