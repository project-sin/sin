package sin.sin.dto.auth;

import lombok.*;
import sin.sin.domain.member.Member;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class JoinResponse {
    private String name;
}