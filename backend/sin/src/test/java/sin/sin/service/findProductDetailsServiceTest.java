package sin.sin.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import sin.sin.domain.product.Product;
import sin.sin.domain.product.ProductRepository;
import sin.sin.dto.ProductDetails.ProductDetailsResponse;
import sin.sin.dto.ProductDetails.ProductQnaResponse;

import javax.transaction.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class findProductDetailsServiceTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private FindProductDetailsService findProductDetailsService;

    @Test
    void findProductDetailsService() {
        //given   import.sql로 생성된 product를 가져옴
        Product product = productRepository.findById(1L)
                .orElseThrow(() -> new IllegalArgumentException("해당되는 Product가 없습니다."));
        //when
        ProductDetailsResponse productDetails = findProductDetailsService
                .findProductDetailsService(product.getProductCode());

        //then
        assertThat(productDetails.getProductName()).isEqualTo(product.getName());
        assertThat(productDetails.getProductInformationResponse().getSaleUnit()).isEqualTo(
                product.getProductDetails().getSaleUnit());
        assertThat(productDetails.getProductInformationResponse().getAllergicReaction()).isNull();
        System.out.println(productDetails);
    }

    @Test
    void 상품_qna() {
        //given
        Product product = productRepository.findById(2L)
                .orElseThrow(() -> new IllegalArgumentException("해당되는 Product가 없습니다."));

        //when
        Page<ProductQnaResponse> qna = findProductDetailsService
                .findProductDetailsQna(product.getProductCode(), 1);

        //then
        assertThat(qna.getTotalElements()).isEqualTo(product.getProductQuestion().size());
    }
}
