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
        List<Product> products = productListService.newProductList();

        products.stream().forEach(product-> System.out.println(product));

    }

    @Test
    public void 알뜰쇼핑() throws Exception {
        List<Product> products = productListService.CheapProductList();

        products.stream().forEach(product-> System.out.println(product));

    }

}