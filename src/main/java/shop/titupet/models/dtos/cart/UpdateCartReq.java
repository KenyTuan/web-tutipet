package shop.titupet.models.dtos.cart;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import shop.titupet.models.dtos.productCart.UpdateProductCartReq;

import java.util.Set;

@Getter
@Setter
public class UpdateCartReq {

    @NotNull(message = "Id required!")
    private Long id;

    @NotNull(message = "Product required!")
    private Set<UpdateProductCartReq> productCartReqs;
}
