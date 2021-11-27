package sin.sin.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sin.sin.domain.cart.Cart;
import sin.sin.domain.cart.CartRepository;
import sin.sin.domain.product.Product;
import sin.sin.domain.product.ProductRepository;
import sin.sin.dto.ProductFrame;
import sin.sin.dto.cart.CartRequest;
import sin.sin.dto.cart.MemberCartResponse;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
class CartServiceTest {
    @Autowired
    CartService cartService;
    @Autowired
    CartRepository cartRepository;
    @Autowired
    ProductRepository productRepository;

    @BeforeEach
    public void clear() {
        cartRepository.deleteAll();
        CartRequest cartRequest = CartRequest.builder()
                .productCode("70").cnt(2).build();
        cartService.add(1L, cartRequest);
    }

    @Test
    void 상품추가() throws Exception {
        //when
        List<Cart> cart = cartRepository.findAll();

        //then
        assertThat(cart.get(0).getCount()).isEqualTo(2);
    }

    @Test
    void 상품_수량_증가() throws Exception {
        //given
        CartRequest cartRequest = CartRequest.builder()
                .productCode("70").cnt(4).build();
        cartService.add(1L, cartRequest);

        //when
        List<Cart> cart = cartRepository.findAll();

        //then
        assertThat(cart.get(0).getCount()).isEqualTo(6);
    }

    @Test
    void 상품_수량_감소() throws Exception {
        //given
        CartRequest cartRequest = CartRequest.builder()
                .productCode("70").cnt(-1).build();
        cartService.add(1L, cartRequest);

        //when
        List<Cart> cart = cartRepository.findAll();

        //then
        assertThat(cart.get(0).getCount()).isEqualTo(1);
    }

    @Test
    void 상품_삭제() throws Exception {
        //given
        CartRequest cartRequest = CartRequest.builder()
                .productCode("95").cnt(1).build();
        cartService.add(1L, cartRequest); //1L의 장바구니에 70, 95 있음

        //when
        cartService.delete(1L, new ArrayList<String>(Arrays.asList("70", "95")));
        List<Cart> cart = cartRepository.findAll();

        //then
        assertThat(cart.size()).isEqualTo(0);
    }

    @Test
    void 회원_상품_조회() throws Exception {
        //given
        List<Product> products = productRepository.findAll();

        CartRequest cartRequest = CartRequest.builder()
                .productCode(products.get(1).getProductCode()).cnt(4).build();
        cartService.add(1L, cartRequest);

        //when
        List<MemberCartResponse> memberCartResponses = cartService.selectMemberCart(1L);

        //then
        assertThat(memberCartResponses.size()).isEqualTo(2);
    }

    @Test
    void 비회원_상품_조회() throws Exception {
        //given
        List<ProductFrame> products = cartService.findProductDetailByProductCode(new ArrayList<>(Arrays.asList("95", "70")));

        //then
        assertThat(products.size()).isEqualTo(2);
    }
}