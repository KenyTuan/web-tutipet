package shop.titupet.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import shop.titupet.models.enums.EnableStatus;
import shop.titupet.models.enums.DiscountType;
import shop.titupet.models.enums.PromotionTarget;

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

//    private PromotionTarget target;

    private ZonedDateTime fromTime;

    private ZonedDateTime toTime;

    @Enumerated(EnumType.STRING)
    private EnableStatus enableStatus;

    @Enumerated(EnumType.STRING)
    private DiscountType discountType;

    private BigDecimal value;

//    @ManyToMany
//    @JoinTable(
//            name = "order_promotion",
//            joinColumns = @JoinColumn(name = "=promotion_id"),
//            inverseJoinColumns = @JoinColumn(name = "order_id")
//    )
//    @JsonIgnore
//    private Set<Order> orders;

//    @ManyToMany
//    @JoinTable(
//            name = "product_promotion",
//            joinColumns = @JoinColumn(name = "promotion_id"),
//            inverseJoinColumns = @JoinColumn(name = "product_id")
//    )
//    @JsonIgnore
//    private Set<Product> products;
}
