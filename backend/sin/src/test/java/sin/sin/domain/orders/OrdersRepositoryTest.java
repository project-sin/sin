package sin.sin.domain.orders;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import sin.sin.domain.RepositoryTest;
import sin.sin.domain.member.Member;
import sin.sin.domain.product.Product;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static sin.sin.domain.SetUpMethods.persistOrderProductList;
import static sin.sin.domain.SetUpMethods.persistOrders;

class OrdersRepositoryTest extends RepositoryTest {

    @Autowired
    private OrdersRepository ordersRepository;

    @Test
    @Transactional
    void findAllByMemberId() {
        //given
        Member member = testEntityManager.find(Member.class, 1L);
        Product product = testEntityManager.find(Product.class, 1L);
        Orders orders = persistOrders(testEntityManager, member);
        persistOrderProductList(testEntityManager, orders, product);
        testEntityManager.flush();
        testEntityManager.clear();

        //when
        List<Orders> ordersList = ordersRepository.findAllByMemberId(member.getId());

        //then
        assertThat(ordersList.size()).isEqualTo(1);
        // join fetch 로 추가적인 db 조회 x
        assertThat(ordersList.get(0).getOrderProductLists().size()).isEqualTo(1);
        // product를 찾기 위해 추가적인 db 조회 1회
        assertThat(ordersList.get(0).getOrderProductLists().get(0).getProduct()
            .getProductCode()).isEqualTo(product.getProductCode());
        // productCategory를 찾기 위해 추가적인 db 조회 1회
        // todo : db 조회 횟수를 줄여야 함
        System.out.println(ordersList.get(0).getOrderProductLists().get(0)
            .getProduct().getProductCategory().getMainCategory());
    }
}