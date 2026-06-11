package bg.softuni.eternalbouquet.model.entity.orderItem;

import bg.softuni.eternalbouquet.model.entity.bouquet.Bouquet;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import bg.softuni.eternalbouquet.model.entity.order.Order;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "order_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private BigDecimal priceAtPurchase;

    @ManyToOne
    private Bouquet bouquet;

    @ManyToOne
    private Order order;
}
