package shop.titupet.dtos.user;

import shop.titupet.models.entities.Address;
import shop.titupet.models.enums.ObjectStatus;
import shop.titupet.models.enums.Role;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Set;

public record UserRes(
        Long id,
        String name,
        String email,
        boolean gender,
        String img,
        LocalDateTime createdAt,
        Long createdBy,

        LocalDateTime updatedAt,

        Long updatedBy,
        ObjectStatus objectStatus
        ) {
}
