package sin.sin.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sin.sin.domain.cart.Cart;
import sin.sin.domain.cart.CartRepository;

import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
class CartServiceTest {
    @Autowired
    CartService cartService;
    @Autowired
    CartRepository cartRepository;

    @BeforeEach
    public void clear(){
        cartRepository.deleteAll();
        cartService.add(1L, "70");
    }

    @Test
    void 상품추가() throws Exception {
        //when
        List<Cart> cart = cartRepository.findAll();

        //then
        assertThat(cart.size()).isEqualTo(1);
    }

    @Test
    void 상품_수량_증가() throws Exception {
        //given
        cartService.add(1L, "70");

        //when
        List<Cart> cart = cartRepository.findAll();

        //then
        assertThat(cart.get(0).getCount()).isEqualTo(2);
    }
}