package bg.softuni.eternalbouquet.model.dto.order;

import bg.softuni.eternalbouquet.model.dto.orderItem.OrderItemDto;
import bg.softuni.eternalbouquet.model.dto.user.UserDto;
import bg.softuni.eternalbouquet.model.entity.order.OrderStatus;
import bg.softuni.eternalbouquet.model.entity.user.User;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class OrderDto {
    private UUID id;
    private LocalDateTime createdOn;
    private BigDecimal totalPrice;
    private OrderStatus status;
    private String deliveryAddress;
    private UserDto user;
    private List<OrderItemDto> items;
}
