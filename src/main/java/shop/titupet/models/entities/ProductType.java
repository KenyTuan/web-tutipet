package shop.titupet.models.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Set;

@Builder @Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product_type")
public class ProductType extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @OneToMany(mappedBy = "type")
    @JsonIgnore
    private Set<Product> products;

}
