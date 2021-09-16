package sin.sin.sqlCreation;

import org.junit.jupiter.api.Test;

public class NotificationSQLTest {
    @Test
    public void 테스트1() throws Exception {

        for(int i=1; i<=41; i++) {
            System.out.print("INSERT INTO Notification (TITLE, CONTENT, WRITER) VALUES");
            System.out.println("('제목" + i + "','내용" + i +"','글쓴이"+i+"');");
        }

    }
}
