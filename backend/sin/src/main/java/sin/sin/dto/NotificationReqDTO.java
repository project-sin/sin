package sin.sin.dto;

import lombok.Builder;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import sin.sin.domain.notification.Notification;

import javax.persistence.Column;
import javax.persistence.Lob;
import java.sql.Timestamp;

@Builder
public class NotificationReqDTO {
    private String title;
    private String content;
    private String writer;

    public Notification toEntity(){
        return Notification.builder()
                .content(content)
                .title(title)
                .writer(writer)
                .build();
    }

}
