package shop.titupet.dtos.payment;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Getter
@Builder
public class PaymentRes implements Serializable {
    private HttpStatus status;
    private String message;
    private String URL;
}
