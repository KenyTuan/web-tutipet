package shop.titupet.models.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder @Getter @Setter
@Table(name = "product_order")
public class ProductOrder extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "quantity",nullable = false)
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "order_id")
//    @JsonIgnoreProperties("productOrders")
    @JsonIgnore
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id")
//    @JsonIgnoreProperties("productOrders")
    @JsonIgnore
    private Product product;

}
