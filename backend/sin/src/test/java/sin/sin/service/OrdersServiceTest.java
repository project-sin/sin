package sin.sin.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sin.sin.domain.member.Member;
import sin.sin.domain.orderProductList.OrderProductList;
import sin.sin.domain.orders.DeliveryStatus;
import sin.sin.domain.orders.OrderStatus;
import sin.sin.domain.orders.Orders;
import sin.sin.domain.product.ProductRepository;
import sin.sin.dto.order.OrderProductsResponse;
import sin.sin.dto.order.OrdersResponse;
import sin.sin.handler.exception.NotExistsMemberException;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
class OrdersServiceTest {

    @Autowired
    private OrdersService ordersService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private EntityManager entityManager;

    private Member member;

    private Orders orders;

    @BeforeEach
    public void setting() {
        member = entityManager.find(Member.class, 1L);

        orders = Orders.builder()
            .member(member)
            .address("주소")
            .deliveryStatus(DeliveryStatus.Ready)
            .orderStatus(OrderStatus.Complete)
            .totalPrice(10000).build();

        OrderProductList orderProductList = OrderProductList.builder()
            .orders(orders)
            .product(productRepository.findAll().get(0))
            .count(10).build();

        orders.addOrderProductList(orderProductList);

        entityManager.persist(orders);
        entityManager.persist(orderProductList);
        entityManager.flush();
        entityManager.clear();
    }

    @Test
    @Transactional
    void findOrdersIds() {
        //when
        List<OrdersResponse> ordersResponses = ordersService.findOrdersIds(member);
        //then
        assertThat(ordersResponses.size()).isEqualTo(1);
        System.out.println(ordersResponses.get(0).getImageUrl());
    }

    @Test
    @Transactional
    void findProductsByOrderId() {
        //when
        List<OrderProductsResponse> products = ordersService.findProductsByOrderId(orders.getId(),
            member);

        //then
        assertThat(products.size()).isEqualTo(1L);
        System.out.println(products.get(0).getImageUrl());

        //given   회원아이디나 주문번호가 잘못된 경우
        Member other = entityManager.find(Member.class, 2L);

        //when && then
        assertThatThrownBy(() -> ordersService.findProductsByOrderId(100L,
            member)).isInstanceOf(NotExistsMemberException.class);
        assertThatThrownBy(() -> ordersService.findProductsByOrderId(orders.getId(),
            other)).isInstanceOf(NotExistsMemberException.class);
    }
}
