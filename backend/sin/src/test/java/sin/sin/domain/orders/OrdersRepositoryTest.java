package sin.sin.domain.orders;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import sin.sin.domain.member.Member;
import sin.sin.domain.orderProductList.OrderProductList;
import sin.sin.domain.product.Product;
import sin.sin.dto.order.OrdersResponse;


@Transactional
@SpringBootTest
class OrdersRepositoryTest {

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    @Transactional
    void findAllByMemberId() {
        //given
        Member member = entityManager.find(Member.class, 1L);
        Product product1 = entityManager.find(Product.class, 1L);
        Product product2 = entityManager.find(Product.class, 2L);
        //주문리스트1
        Orders orders = Orders.builder()
            .member(member)
            .address("주소")
            .deliveryStatus(DeliveryStatus.Ready)
            .orderStatus(OrderStatus.Complete)
            .totalPrice(10000).build();
        entityManager.persist(orders);

        OrderProductList orderProductList1 = OrderProductList.builder()
            .orders(orders)
            .product(product1)
            .count(10).build();
        entityManager.persist(orderProductList1);

        OrderProductList orderProductList2 = OrderProductList.builder()
            .orders(orders)
            .product(product2)
            .count(10).build();
        entityManager.persist(orderProductList2);

        //주문리스트2
        Orders orders2 = Orders.builder()
            .member(member)
            .address("주소")
            .deliveryStatus(DeliveryStatus.Ready)
            .orderStatus(OrderStatus.Complete)
            .totalPrice(10000).build();
        entityManager.persist(orders2);

        OrderProductList orderProductList3 = OrderProductList.builder()
            .orders(orders2)
            .product(product1)
            .count(10).build();
        entityManager.persist(orderProductList3);

        entityManager.flush();
        entityManager.clear();

        //when
        List<OrdersResponse> ordersList = ordersRepository.findOrdersByMemberId(member.getId());

        //then
        assertThat(ordersList.get(0).getOrdersId()).isEqualTo(
            orders2.getId());
        assertThat(ordersList.get(0).getProductName()).isEqualTo(
            product1.getName());
        assertThat(ordersList.get(1).getProductName()).isEqualTo(
            product2.getName());
        System.out.println(ordersList.get(0).getImageUrl());
        System.out.println(ordersList.get(1).getImageUrl());
    }
}
