package sin.sin.domain.orders;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import sin.sin.domain.member.Member;
import sin.sin.domain.orderProductList.OrderProductList;
import sin.sin.domain.product.Product;
import sin.sin.dto.order.OrderProductsResponse;
import sin.sin.dto.order.OrdersResponse;


@Transactional
@SpringBootTest
class OrdersRepositoryTest {

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private EntityManager entityManager;

    Member member;
    Product product1;
    Product product2;
    Orders orders;

    @BeforeEach
    void setup(){
        member = entityManager.find(Member.class, 1L);
        product1 = entityManager.find(Product.class, 1L);
        product2 = entityManager.find(Product.class, 2L);

        //주문리스트1
        orders = Orders.builder()
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

        entityManager.flush();
        entityManager.clear();
    }

    @Test
    @Transactional
    void findAllByMemberId() {
        //given  -- 주문리스트2
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
        List<Orders> ordersList = ordersRepository.findOrdersByMemberId(member.getId());

        //then
        assertThat(ordersList.get(0).getId()).isEqualTo(
            orders2.getId());

    }

    @Test
    void findProductsByOrdersId(){
        //when
        List<OrderProductsResponse> products = ordersRepository.findProductsByOrdersId(orders.getId());

        //then
        assertThat(products.size()).isEqualTo(2L);
    }

    @Test
    void checkMember(){
        //when
        boolean checkTrue = ordersRepository.checkMember(member.getId(), orders.getId());
        boolean checkFalse1 = ordersRepository.checkMember(100L, orders.getId());
        boolean checkFalse2 = ordersRepository.checkMember(member.getId(), 100L);

        //then
        assertThat(checkTrue).isEqualTo(true);
        assertThat(checkFalse1).isEqualTo(false);
        assertThat(checkFalse2).isEqualTo(false);
    }
}
