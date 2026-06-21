package bg.softuni.eternalbouquet.model.dto.order;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class CheckoutRequest {
    private String deliveryAddress;
}
