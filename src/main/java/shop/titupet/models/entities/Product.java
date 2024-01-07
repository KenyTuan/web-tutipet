package shop.titupet.models.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import shop.titupet.models.enums.EnableStatus;
import shop.titupet.models.enums.PetType;

import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Table(name = "product")
@Builder
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private PetType petTypes;

    private String name;

    private String description;

    private String info;

    private double price;

    private String img;

    @Enumerated(EnumType.STRING)
    private EnableStatus status;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private ProductType type;

    @OneToMany(mappedBy = "product")
    private Set<ProductOrder> productOrder;



}
