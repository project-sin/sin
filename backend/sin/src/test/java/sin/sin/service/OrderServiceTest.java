/*
package sin.sin.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;
import javax.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sin.sin.domain.member.Member;
import sin.sin.domain.member.MemberRepository;
import sin.sin.domain.orderProductList.OrderProductList;
import sin.sin.domain.orderProductList.OrderProductListRepository;
import sin.sin.domain.orders.DeliveryStatus;
import sin.sin.domain.orders.OrderStatus;
import sin.sin.domain.orders.Orders;
import sin.sin.domain.product.ProductRepository;
import sin.sin.dto.order.OrdersResponse;
import sin.sin.handler.exception.NotExistsMemberException;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private Orders2Repository ordersRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private OrderProductListRepository orderProductListRepository;

    private Member member;

    @BeforeEach
    public void setting() {
        ordersRepository.deleteAll();
        member = memberRepository.findBy_id("a")
            .orElseThrow(() -> new NotExistsMemberException("해당 회원존재x"));

        Orders orders = Orders.builder()
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
        ordersRepository.save(orders);
        orderProductListRepository.save(orderProductList);
    }

    @Test
    @Transactional
    void findOrdersIds() {
        //when
        List<OrdersResponse> ordersResponses = orderService.findOrdersIds(member);
        //then
        assertThat(ordersResponses.size()).isEqualTo(1);
        System.out.println(ordersResponses.get(0).getImageUrl());
    }
}*/
