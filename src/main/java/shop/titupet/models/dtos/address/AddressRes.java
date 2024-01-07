package shop.titupet.models.dtos.address;

import jakarta.validation.constraints.NotBlank;

public record AddressRes(
        String receiverName,
        String address,
        String phone
) {
}
