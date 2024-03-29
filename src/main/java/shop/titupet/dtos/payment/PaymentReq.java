package shop.titupet.dtos.payment;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;

@Getter
public class PaymentReq {

    @Positive(message = "Amount Required!")
    private Integer amount;

}
