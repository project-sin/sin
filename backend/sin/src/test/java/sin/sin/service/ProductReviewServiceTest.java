package sin.sin.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import sin.sin.domain.product.Product;
import sin.sin.domain.product.ProductRepository;
import sin.sin.dto.ProductDetails.ProductReviewResponse;

import javax.transaction.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
class ProductReviewServiceTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductReviewService productReviewService;

    @Test
    void 상품_리뷰_조회(){
        //given
        Product product = productRepository.findById(1L)
                .orElseThrow(() -> new IllegalArgumentException("해당되는 Product가 없습니다."));

        //when
        Page<ProductReviewResponse> review = productReviewService
                .findProductReview(product.getProductCode(), 1);

        //then
        assertThat(review.getTotalElements()).isEqualTo(product.getProductReview().size());
    }
}