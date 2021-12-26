package sin.sin.dto.order;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sin.sin.domain.orders.OrderStatus;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class OrdersDto {

    private Long ordersId;
    private int totalPrice;
    private OrderStatus orderStatus;
    private Long count;

    @QueryProjection
    public OrdersDto(Long orderId, int totalPrice, OrderStatus orderStatus, Long count) {
        this.ordersId = orderId;
        this.totalPrice = totalPrice;
        this.orderStatus = orderStatus;
        this.count = count;
    }
}
