package shop.titupet.models.dtos.product;

import shop.titupet.models.entities.ProductType;
import shop.titupet.models.entities.Promotion;
import shop.titupet.models.enums.EnableStatus;
import shop.titupet.models.enums.ObjectStatus;
import shop.titupet.models.enums.PetType;

import java.time.ZonedDateTime;

public record ProductRes( Long id,
                          String name,
                          PetType petType,
                          double price,
                          String description,
                          String info,
                          String img,
                          EnableStatus status,
                          Long createdBy,
                          ZonedDateTime createdAt,
                          Long updatedBy,
                          ZonedDateTime updatedAt,
                          ObjectStatus objectStatus,
                          ProductType type,
                          Promotion promotion
) {
}
