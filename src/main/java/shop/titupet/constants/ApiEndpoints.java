package shop.titupet.constants;


import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ApiEndpoints {
    public static final String PREFIX = "/api";
    public static final String USER_V1 = "/v1/users";
    public static final String ORDER_V1 = "/v1/orders";
    public static final String PRO_TYPE_V1 = "/v1/types";
    public static final String PRODUCT_V1 = "/v1/products";
    public static final String ADDRESS_V1 = "/v1/address";
    public static final String PROMOTION_V1 = "/v1/promotion";
    public static final String CART_V1 = "/v1/cart";
    public static final String AUTH_V1 = "/v1/auth";
}
