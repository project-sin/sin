package sin.sin.domain.notification;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    Page<Notification> findAll(Pageable pageable);
    Optional<Page<Notification>> findByTitleOrContentOrWriter(String title, String content, String writer, Pageable pageable);

    @Query(value="SELECT * FROM Notification WHERE notification_id IN(:id, " +
            "(SELECT MAX(notification_id) FROM Notification WHERE notification_id<:id), " +
            "(SELECT MIN(notification_id) FROM Notification WHERE notification_id>:id))", nativeQuery = true)
    List<Notification> findByIdNotifications(Long id);

    @Modifying
    @Query("UPDATE Notification n SET n.views=n.views+1 WHERE n.id=:id")
    int updateById(Long id);
}
