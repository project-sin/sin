package sin.sin.domain.notification;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    Page<Notification> findAll(Pageable pageable);
    Optional<Page<Notification>> findByTitleOrContentOrWriter(String title, String content, String writer, Pageable pageable);
}
