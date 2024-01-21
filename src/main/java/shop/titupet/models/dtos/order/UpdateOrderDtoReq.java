package shop.titupet.models.dtos.order;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import shop.titupet.models.enums.OrderStatus;

@Getter
@Setter
public class UpdateOrderDtoReq {

    @NotNull(message = "Id is required.")
    private Long id;

    @NotNull(message = "User Id is required.")
    private Long user_id;

    private OrderStatus status;

}
