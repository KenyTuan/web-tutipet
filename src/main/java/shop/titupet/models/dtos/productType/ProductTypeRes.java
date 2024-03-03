package shop.titupet.models.dtos.productType;

import shop.titupet.models.entities.Product;
import shop.titupet.models.enums.ObjectStatus;
import shop.titupet.models.enums.PetType;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Set;

public record ProductTypeRes(
        Integer id,
        String name,
        PetType petType,
        Set<Product> product,
        Long createdBy,
        ZonedDateTime createdAt,
        Long updatedBy,
        ZonedDateTime updatedAt,
        ObjectStatus objectStatus
) {
}
