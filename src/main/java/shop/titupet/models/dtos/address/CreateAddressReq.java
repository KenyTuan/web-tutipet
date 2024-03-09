package shop.titupet.models.dtos.address;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import shop.titupet.models.entities.User;

@Getter @Setter
public class CreateAddressReq {

    @NotBlank(message = "Name is required.")
    @Size(min = 5,max = 255,message = "Receiver name length ranges from 5 to 255 characters")
    private String receiverName;

    @NotBlank(message = "Address is required.")
    private String address;

    @NotBlank(message = "Phone is required.")
    private String phone;

}
