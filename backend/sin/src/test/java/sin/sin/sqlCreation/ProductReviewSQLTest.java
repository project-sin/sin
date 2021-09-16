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
        for(int i=1; i<=4; i++) {
            System.out.print("INSERT INTO PRODUCT_REVIEW (TITLE, CONTENT, PRODUCT_ID, MEMBER_ID) VALUES");
            System.out.println("('제목" + i + "','내용" + i +"', '1', '1');");
        }

        for(int i=5; i<=7; i++) {
            System.out.print("INSERT INTO PRODUCT_REVIEW (TITLE, CONTENT, PRODUCT_ID, MEMBER_ID) VALUES");
            System.out.println("('제목" + i + "','내용" + i +"', '2', '1');");
        }

        for(int i=8; i<=10; i++) {
            System.out.print("INSERT INTO PRODUCT_REVIEW (TITLE, CONTENT, PRODUCT_ID, MEMBER_ID) VALUES");
            System.out.println("('제목" + i + "','내용" + i +"', '3', '1');");
        }

        for(int i=11; i<=11; i++) {
            System.out.print("INSERT INTO PRODUCT_REVIEW (TITLE, CONTENT, PRODUCT_ID, MEMBER_ID) VALUES");
            System.out.println("('제목" + i + "','내용" + i +"', '4', '1');");
        }


    }
}
