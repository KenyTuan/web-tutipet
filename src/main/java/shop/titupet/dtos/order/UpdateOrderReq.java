package shop.titupet.dtos.order;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import shop.titupet.models.enums.OrderStatus;

@Getter
@Setter
public class UpdateOrderReq {

    @NotNull(message = "Id is required.")
    private Long id;

    @NotNull(message = "status is required.")
    private OrderStatus status;

}
