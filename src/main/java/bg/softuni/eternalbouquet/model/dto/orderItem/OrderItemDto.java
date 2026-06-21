package bg.softuni.eternalbouquet.model.dto.orderItem;

import bg.softuni.eternalbouquet.model.dto.bouquet.BouquetDto;
import bg.softuni.eternalbouquet.model.dto.order.OrderDto;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
public class OrderItemDto {
    private UUID id;
    private Integer quantity;
    private BigDecimal priceAtPurchase;
    private BouquetDto bouquet;
    private OrderDto order;
}
