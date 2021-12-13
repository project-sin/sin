package sin.sin.sqlCreation;

import org.junit.jupiter.api.Test;

public class ProductReviewSQLTest {
    @Test
    public void 테스트1() throws Exception {

        //레벨
        System.out.print("INSERT INTO LEVEL (SALE_PERCENT) VALUES");
        System.out.println("('20');");

        //멤버
        System.out.print("INSERT INTO Member (PASSWORD, NAME, EMAIL, PHONE_NUMBER, GENDER, ADDRESS, BIRTH, LEVEL_ID) VALUES");
        System.out.println("('a', '글쓴이1','이메일1','폰번호1','Male','집주소1','생일1', '1');");

        //상품 후기
        int productId = 1;
        for (int i = 1; i <= 11; i++) {
            if (5 <= i && i <= 7) productId = 2;
            else if (7 < i && i <= 10) productId = 3;
            else if (10 < i) productId = 4;

            System.out.print("INSERT INTO PRODUCT_REVIEW (TITLE, CONTENT, PRODUCT_ID, MEMBER_ID, CREATED_DATE) VALUES");
            System.out.println("('제목" + i + "','내용" + i + "', " + productId + ", '1'"+", TO_CHAR(SYSTIMESTAMP-" + i + ", 'YYYY-MM-DD HH:MI:SS.FF3'));");
        }

        productId = 5;
        for (int i = 11; i <= 22; i++) {
            if (15 <= i && i <= 17) productId = 6;
            else if (17 < i && i <= 20) productId = 7;
            else if (20 < i) productId = 8;

            System.out.print("INSERT INTO PRODUCT_REVIEW (TITLE, CONTENT, PRODUCT_ID, MEMBER_ID, CREATED_DATE) VALUES");
            System.out.println("('제목" + i + "','내용" + i + "', " + productId + ", '2'"+", TO_CHAR(SYSTIMESTAMP-" + i + ", 'YYYY-MM-DD HH:MI:SS.FF3'));");
        }


    }
}
