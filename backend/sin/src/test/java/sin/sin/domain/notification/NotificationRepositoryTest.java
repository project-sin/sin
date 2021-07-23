package sin.sin.domain.notification;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional(readOnly = true)
@SpringBootTest
class NotificationRepositoryTest {

    @Autowired NotificationRepository notificationRepository;

    @Test
    public void 이전글_다음글_조회() throws Exception {
        List<Notification> notifications = notificationRepository.findByIdNotifications(40L);

        assertThat(notifications.size()).isEqualTo(3);
    }
}