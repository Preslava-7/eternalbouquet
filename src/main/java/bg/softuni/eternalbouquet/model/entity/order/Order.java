package bg.softuni.eternalbouquet.model.entity.order;

import bg.softuni.eternalbouquet.model.entity.orderItem.OrderItem;
import bg.softuni.eternalbouquet.model.entity.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private LocalDateTime createdOn;

    @Column(nullable = false)
    private BigDecimal totalPrice;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private String deliveryAddress;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> items = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        this.createdOn = LocalDateTime.now();
    }
}
