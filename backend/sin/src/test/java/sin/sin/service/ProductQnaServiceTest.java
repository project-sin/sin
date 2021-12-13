package sin.sin.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import sin.sin.domain.product.Product;
import sin.sin.domain.product.ProductRepository;
import sin.sin.dto.ProductDetails.ProductQnaResponse;

import javax.transaction.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
class ProductQnaServiceTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductQnaService productQnaService;

    @Test
    void 상품_qna_조회() {
        //given
        Product product = productRepository.findById(2L)
                .orElseThrow(() -> new IllegalArgumentException("해당되는 Product가 없습니다."));

        //when
        Page<ProductQnaResponse> qna = productQnaService
                .findProductQna(product.getProductCode(), 1);

        //then
        assertThat(qna.getTotalElements()).isEqualTo(product.getProductQuestion().size());
    }

}