package shop.titupet.models.dtos.address;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UpdateAddressReq {

    private Long id;

    @NotBlank(message = "Name is required.")
    private String receiverName;

    @NotBlank(message = "Address is required.")
    private String address;

    @NotBlank(message = "Phone is required.")
    private String phone;

}
