package sin.sin.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sin.sin.domain.product.Product;
import sin.sin.domain.product.ProductRepository;
import sin.sin.dto.ProductListResponse;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class ProductListServiceTest {
    @Autowired
    ProductListService productListService;

    @Test
    public void 이번달_신상품() {
        //given
        List<Product> products = productListService.newProductList();

        //when
        Timestamp first = products.get(0).getCreatedDate();
        Timestamp second = products.get(1).getCreatedDate();

        //then
        //첫 상품의 createdDate >= 두번째 상품의 createdDate
        assertThat(first).isAfterOrEqualTo(second);
    }

    @Test
    public void 후기순_베스트상품_20() {
        //given
        List<ProductListResponse> products = productListService.BestProductList();

        //when
        int first = products.get(0).getReviewCount();
        int second = products.get(1).getReviewCount();

        //then
        //첫 상품의 reviewCount >= 두번째 상품의 reviewCount
        assertThat(first).isGreaterThanOrEqualTo(second);

    }

    @Test
    public void 알뜰쇼핑() {
        //given
        List<Product> products = productListService.CheapProductList();

        //when
        int first = products.get(0).getDiscountPercent();
        int second = products.get(1).getDiscountPercent();

        //then
        //첫 상품의 discountPercent >= 두번째 상품의 discountPercent
        assertThat(first).isGreaterThanOrEqualTo(second);
    }

}