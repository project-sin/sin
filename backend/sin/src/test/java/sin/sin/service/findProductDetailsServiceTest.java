package sin.sin.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import javax.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sin.sin.domain.product.Product;
import sin.sin.domain.product.ProductRespository;
import sin.sin.dto.ProductDetailsResponse;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class findProductDetailsServiceTest {

    @Autowired
    private ProductRespository productRespository;

    @Autowired
    private FindProductDetailsService findProductDetailsService;

    @Test
    void findProductDetailsService() {
        //given   import.sql로 생성된 product를 가져옴
        Product product = productRespository.findById(1L)
            .orElseThrow(() -> new IllegalArgumentException("해당되는 Product가 없습니다."));
        //when
        ProductDetailsResponse productDetails = findProductDetailsService
            .findProductDetailsService(product.getId());

        //then
        assertThat(productDetails.getProductName()).isEqualTo(product.getName());
        assertThat(productDetails.getDetailedInformation().size()).isEqualTo(5);
        System.out.println(productDetails);
    }
}
