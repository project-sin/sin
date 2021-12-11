package sin.sin.dto.order;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sin.sin.domain.orders.OrderStatus;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
public class OrdersResponse {

    private Long ordersId;
    private int totalPrice;
    private OrderStatus orderStatus;
    private String productName;
    private Long productCount;
    private String imageUrl;
}
