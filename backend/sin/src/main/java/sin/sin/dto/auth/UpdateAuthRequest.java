package sin.sin.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sin.sin.domain.member.Gender;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAuthRequest {

    private String password;
    private String name;
    private String email;
    private String phoneNumber;
    private String birth;
    private Gender gender;
}
