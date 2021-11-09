package sin.sin.dto.auth;

import com.sun.istack.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    @NotNull
    private String id;

    @NotNull
    private String password;
}
