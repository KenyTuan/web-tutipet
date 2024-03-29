package shop.titupet.dtos.address;

import jakarta.validation.constraints.NotBlank;
import shop.titupet.models.enums.ObjectStatus;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

public record AddressRes(
        Long id,
        String receiverName,
        String address,
        String phone,
        LocalDateTime createdAt,
        Long createdBy,
        LocalDateTime updatedAt,
        Long updatedBy,
        ObjectStatus objectStatus
) {
}
