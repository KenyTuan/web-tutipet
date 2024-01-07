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


}
