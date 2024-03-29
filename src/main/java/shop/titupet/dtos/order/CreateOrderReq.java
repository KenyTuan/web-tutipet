package shop.titupet.dtos.order;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import shop.titupet.dtos.productorder.ProductOrderReq;

import java.util.Set;

@Getter @Setter
public class CreateOrderReq {

    @NotNull(message = "Address Id is required.")
    private Long address_id;

    private String note;

    @NotNull(message = "List product order is required.")
    private Set<ProductOrderReq> productOrderReqs;

    private Set<Long> promotionCodes;

}
