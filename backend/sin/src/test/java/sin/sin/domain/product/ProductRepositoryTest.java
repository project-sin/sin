package sin.sin.domain.product;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;

    @Test
    void 상품_검색() {
        //given
        System.out.println(productRepository.findAll());
        //when

        //then

    }

}