package bg.softuni.eternalbouquet.mapper.order;

import bg.softuni.eternalbouquet.mapper.orderItem.OrderItemMapper;
import bg.softuni.eternalbouquet.model.dto.order.OrderDto;
import bg.softuni.eternalbouquet.model.entity.order.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderMapper {

    public static OrderDto toDto(Order order) {

        if (order == null) {
            return null;
        }

        return OrderDto.builder()
                .id(order.getId())
                .createdOn(order.getCreatedOn())
                .totalPrice(order.getTotalPrice())
                .status(order.getStatus())
                .deliveryAddress(order.getDeliveryAddress())
                .items(
                        order.getItems() == null
                                ? List.of()
                                : order.getItems()
                                .stream()
                                .map(OrderItemMapper::toDto)
                                .toList()
                )
                .build();
    }

}
