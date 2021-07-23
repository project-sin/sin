package sin.sin.dto;

import lombok.Builder;
import sin.sin.domain.notification.Notification;

@Builder
public class NotificationRequest {
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
