package shop.titupet.models.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import shop.titupet.models.enums.EnableStatus;
import shop.titupet.models.enums.PetType;

import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Table(name = "_products")
@Builder
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private String info;

    private double price;

    private String img;

    @Enumerated(EnumType.STRING)
    private EnableStatus status;

    @ManyToOne
    @JoinColumn(name = "type_id")
    @JsonIgnore
    private ProductType type;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private Set<ProductOrder> productOrders;

    @ManyToMany(mappedBy = "products")
    @JsonIgnore
    private Set<Promotion> promotions;

}
