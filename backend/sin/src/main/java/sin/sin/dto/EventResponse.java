package sin.sin.dto;

import lombok.*;
import sin.sin.domain.event.Classification;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@ToString
@Getter
public class EventResponse {

    private String fileCode;
    private String imageUrl;
    private Classification classification;
}
