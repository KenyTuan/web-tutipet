package shop.titupet.models.dtos.address;

import jakarta.validation.constraints.NotBlank;
import shop.titupet.models.enums.ObjectStatus;

import java.time.ZonedDateTime;

public record AddressRes(
        Long id,
        String receiverName,
        String address,
        String phone,
        ZonedDateTime createdAt,
        Long createdBy,
        ZonedDateTime updatedAt,
        Long updatedBy,
        ObjectStatus objectStatus
) {
}
