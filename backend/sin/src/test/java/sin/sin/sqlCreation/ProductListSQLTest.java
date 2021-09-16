package sin.sin.sqlCreation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sin.sin.domain.level.LevelRepository;
import sin.sin.domain.member.MemberRepository;
import sin.sin.domain.product.ProductRepository;
import sin.sin.domain.productReview.ProductReviewRepostiory;

@SpringBootTest
public class ProductListSQLTest {

    @Test
    public void test() throws Exception {
        //InStock
        for (int i = 0; i <= 30; i++) {
            System.out.print("INSERT INTO PRODUCT (NAME, PRICE, STATUS, DISCOUNT_PERCENT, CREATED_DATE) VALUES");
            System.out.println("('상품" + i + "'," + i * 1000 + ",'InStock'," + i + "," + "TO_CHAR(SYSTIMESTAMP-" + i + ", 'YYYY-MM-DD HH:MI:SS.FF3'));");
        }

        //OutOfStock
        for (int i = 31; i <= 41; i++) {
            System.out.print("INSERT INTO PRODUCT (NAME, PRICE, STATUS, DISCOUNT_PERCENT, CREATED_DATE) VALUES");
            System.out.println("('상품" + i + "'," + i * 1000 + ",'InStock'," + i + "," + "TO_CHAR(SYSTIMESTAMP-" + i + ", 'YYYY-MM-DD HH:MI:SS.FF3'));");
        }

    }
}
