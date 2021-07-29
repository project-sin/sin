package sin.sin.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class NotificationResponse {
        private Long id;
        private String title;
}
