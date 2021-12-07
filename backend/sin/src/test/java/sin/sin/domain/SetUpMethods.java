package sin.sin.domain;

import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import sin.sin.domain.member.Member;
import sin.sin.domain.orderProductList.OrderProductList;
import sin.sin.domain.orders.DeliveryStatus;
import sin.sin.domain.orders.OrderStatus;
import sin.sin.domain.orders.Orders;
import sin.sin.domain.product.Product;

public class SetUpMethods {

    public static Orders persistOrders(TestEntityManager testEntityManager, Member member) {
        Orders orders = Orders.builder()
            .member(member)
            .address("주소")
            .deliveryStatus(DeliveryStatus.Ready)
            .orderStatus(OrderStatus.Complete)
            .totalPrice(10000).build();
        return testEntityManager.persist(orders);
    }

    public static OrderProductList persistOrderProductList(TestEntityManager testEntityManager,
        Orders orders, Product product) {
        OrderProductList orderProductList = OrderProductList.builder()
            .orders(orders)
            .product(product)
            .count(10).build();
        return testEntityManager.persist(orderProductList);
    }
}
