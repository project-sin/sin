package sin.sin.domain.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sin.sin.dto.ProductMain.MainCategoryResponse;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findProductByProductCode(String productCode);
    @Query(value = "SELECT NAME, MAIN_CATEGORY MAINCATEGORY, SUB_CATEGORY SUBCATEGORY, DISCOUNT_PERCENT DISCOUNTPERCENT, PRICE, PRODUCT_CODE PRODUCTCODE, STATUS " +
            "FROM (SELECT *, RANK() OVER (PARTITION BY MAIN_CATEGORY ORDER BY 리뷰수 DESC, CREATED_DATE DESC) rn " +
            "      FROM (SELECT p.NAME, " +
            "                   count(pr.PRODUCT_REVIEW_ID) 리뷰수, " +
            "                   pc.MAIN_CATEGORY, " +
            "                   p.CREATED_DATE, " +
            "                   p.DISCOUNT_PERCENT, " +
            "                   p.PRICE, " +
            "                   p.PRODUCT_CODE, " +
            "                   p.STATUS, " +
            "                   pc.SUB_CATEGORY " +
            "            FROM PRODUCT p " +
            "                     JOIN PRODUCT_CATEGORY pc USING (PRODUCT_CATEGORY_ID) " +
            "                     LEFT JOIN PRODUCT_REVIEW pr on pr.PRODUCT_ID=p.PRODUCT_ID " +
            "            GROUP BY p.PRODUCT_ID)) " +
            "WHERE rn <= 4", nativeQuery = true)
    List<MainCategoryResponse> findTop4ByOrderByProductReviewDesc();
}
