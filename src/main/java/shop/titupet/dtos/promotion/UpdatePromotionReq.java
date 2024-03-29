package shop.titupet.dtos.promotion;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import shop.titupet.models.enums.DiscountType;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

@Getter @Setter
public class UpdatePromotionReq {

    @NotNull(message = "Id is required,")
    private Long id;

    @NotNull(message = "Form Time is required.")
    private ZonedDateTime fromTime;

    @NotNull(message = "To Time is required.")
    private ZonedDateTime toTime;

    @NotNull(message = "Discount Type is required.")
    private DiscountType discountType;

    @Positive(message = "Value must greater than 0.")
    private BigDecimal value;

    private List<Long> productIds;
}
