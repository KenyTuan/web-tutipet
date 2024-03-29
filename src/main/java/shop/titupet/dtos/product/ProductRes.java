package shop.titupet.dtos.product;

import shop.titupet.models.entities.ProductType;
import shop.titupet.models.entities.Promotion;
import shop.titupet.models.enums.EnableStatus;
import shop.titupet.models.enums.ObjectStatus;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

public record ProductRes( Long id,
                          String name,
                          double price,
                          String description,
                          String info,
                          String img,
                          EnableStatus status,
                          Long createdBy,
                          LocalDateTime createdAt,
                          Long updatedBy,
                          LocalDateTime updatedAt,
                          ObjectStatus objectStatus,
                          ProductType type,
                          Promotion promotion
) {
}
