package shop.titupet.models.dtos.cart;

import lombok.Getter;
import lombok.Setter;
import shop.titupet.models.dtos.productCart.ProductCartReq;

import java.util.Set;

@Getter @Setter
public class CartReq {

    private Long userId;

    private Set<ProductCartReq> productCartReqs;
}
