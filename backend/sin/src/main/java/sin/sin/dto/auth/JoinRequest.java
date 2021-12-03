package sin.sin.dto.auth;

import lombok.*;
import sin.sin.domain.level.Level;
import sin.sin.domain.member.Gender;
import sin.sin.domain.member.Member;

import javax.validation.constraints.NotEmpty;


@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class JoinRequest {
    @NotEmpty
    private String id;
    @NotEmpty
    private String password;
    @NotEmpty
    private String name;
    @NotEmpty
    private String email;
    @NotEmpty
    private String phone_number;
    @NotEmpty
    private String address;
    @NotEmpty
    private Gender gender;
    @NotEmpty
    private String birth;
    private Level level;
    private boolean private_info;
    private boolean ad_sms;
    private boolean ad_email;
    private String referral_id;
    private String event;

    public Member toMemberEntity() {
        return Member.builder()
                ._id(id)
                .password(password)
                .name(name)
                .email(email)
                .phoneNumber(phone_number)
                .address(address)
                .gender(gender)
                .birth(birth)
                .level(level)
                .adSms(ad_sms)
                .adEmail(ad_email)
                .privateInfo(private_info)
                .build();
    }
}
