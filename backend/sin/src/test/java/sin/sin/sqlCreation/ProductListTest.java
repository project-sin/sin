package sin.sin.sqlCreation;

import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.util.Calendar;

public class ProductListTest {

    @Test
    public void test() throws Exception {
        //InStock
        for(int i=1; i<=30; i++) {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis() - 86400000 * i*3);
            System.out.print("INSERT INTO PRODUCT (NAME, PRICE, STATUS, DISCOUNT_PERCENT, CREATED_DATE) VALUES");
            System.out.println("('상품" + i + "'," + i*1000 +",'InStock',"+i+",'"+timestamp+"');");
        }

        //OutOfStock
        for(int i=31; i<=41; i++) {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis() - 86400000 * i*3);
            System.out.print("INSERT INTO PRODUCT (NAME, PRICE, STATUS, DISCOUNT_PERCENT, CREATED_DATE) VALUES");
            System.out.println("('상품" + i + "'," + i*1000 +",'OutOfStock',"+i+",'"+timestamp+"');");
        }
    }
}
