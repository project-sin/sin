package sin.sin.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sin.sin.domain.product.Product;

import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest
@Transactional
class ProductListServiceTest {
    @Autowired ProductListService productListService;

    @Test
    public void 이번달_신상품() throws Exception {
        //given
        List<Product> products = productListService.newProductList();

        //when
        products.stream().forEach(product-> System.out.println(product));

    }

}