package bg.softuni.eternalbouquet.mapper.orderItem;

import bg.softuni.eternalbouquet.mapper.bouquet.BouquetMapper;
import bg.softuni.eternalbouquet.model.dto.orderItem.OrderItemDto;
import bg.softuni.eternalbouquet.model.entity.orderItem.OrderItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderItemMapper {

    public static OrderItemDto toDto(OrderItem item){

        if(item == null){
            return null;
        }

        return OrderItemDto.builder()
                .id(item.getId())
                .quantity(item.getQuantity())
                .bouquet(BouquetMapper.toDto(item.getBouquet()))
                .priceAtPurchase(item.getPriceAtPurchase())
                .build();
    }

}
