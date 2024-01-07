package shop.titupet.models.entities;

import jakarta.persistence.*;
import lombok.*;
import shop.titupet.models.enums.EnableStatus;
import shop.titupet.models.enums.DiscountType;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Set;

@Entity
@Table(name = "promotion")
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
public class Promotion extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    private ZonedDateTime fromTime;

    private ZonedDateTime toTime;

    @Enumerated(EnumType.STRING)
    private EnableStatus enableStatus;

    @Enumerated(EnumType.STRING)
    private DiscountType discountType;

    private BigDecimal value;

    @OneToMany(mappedBy = "promotion")
    private Set<ProductOrder> productOrders;

}
