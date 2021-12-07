package sin.sin.sqlCreation;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProductQuestionSQLTest {

    @Test
    public void test() throws Exception {
        String isSecret = null;
        String member = null;

        //product_question
        for (int i = 1; i <= 45; i++) { //전체 product 개수 45개
            for (int j = 1; j <= i % 3; j++) {
                if (j % 2 == 0) {
                    isSecret = "nonSecret";
                    member = "1";
                } else {
                    isSecret = "Secret";
                    member = "2";
                }

                System.out.print("INSERT INTO PRODUCT_QUESTION (TITLE, CONTENT, SECRET, MEMBER_ID, PRODUCT_ID, CREATED_DATE) VALUES");
                System.out.println("('질문" + j + "', '내용" + j + "', '" + isSecret + "'," + member + "," + i + ", TO_CHAR(SYSTIMESTAMP-" + (j - 1) + ", 'YYYY-MM-DD HH:MI:SS.FF3'));");
            }

        }

        for (int i = 1; i <= 45; i++) { //전체 product_question 개수 45개
            System.out.print("INSERT INTO PRODUCT_QUESTION_REPLY (CONTENT, PRODUCT_QUESTION_ID, CREATED_DATE) VALUES");
            System.out.println("('답변" + i + "', '" + i + "', TO_CHAR(SYSTIMESTAMP-" + 0 + ", 'YYYY-MM-DD HH:MI:SS.FF3'));");

        }

    }
}
