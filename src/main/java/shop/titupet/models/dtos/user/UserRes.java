package shop.titupet.models.dtos.user;

import shop.titupet.models.entities.Address;
import shop.titupet.models.enums.ObjectStatus;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Set;

public record UserRes(
        Long id,
        String name,
        String email,
        boolean gender,
        String img,
        ZonedDateTime createdAt,
        Long createdBy,

        ZonedDateTime updatedAt,

        Long updatedBy,
        ObjectStatus objectStatus
        ) {
}
