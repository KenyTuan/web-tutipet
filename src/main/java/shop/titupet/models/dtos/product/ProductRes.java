package shop.titupet.models.dtos.product;

import shop.titupet.models.enums.EnableStatus;
import shop.titupet.models.enums.PetType;

public record ProductRes(PetType petType,
                         String name,
                         String description,
                         String info,
                         double price,
                         String img,
                         EnableStatus status) {
}
