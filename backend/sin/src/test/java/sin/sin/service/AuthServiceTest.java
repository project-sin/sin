package sin.sin.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.annotation.Transactional;
import sin.sin.domain.member.Gender;
import sin.sin.domain.member.Member;
import sin.sin.domain.member.MemberRepository;
import sin.sin.dto.auth.UpdateAuthRequest;

@ExtendWith(MockitoExtension.class)
@Transactional
public class AuthServiceTest {

    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private AuthService authService;

    @Test
    void updateAuth() {
        //given
        String prevName = "이전 이름";
        Member member = Member.builder()
            ._id("test")
            .password("password")
            .name(prevName)
            .email("email")
            .phoneNumber("010-1111-1111")
            .gender(Gender.Male)
            .birth("1999-99-99")
            .build();

        String newName = "변경된 이름";
        UpdateAuthRequest request = UpdateAuthRequest.builder()
            .name(newName)
            .gender(Gender.Female)
            .build();

        given(memberRepository.findById(member.getId())).willReturn(java.util.Optional.of(member));

        //when
        authService.updateAuth(member, request);

        //then
        assertThat(member.getName()).isEqualTo(newName);
        assertThat(member.getGender()).isEqualTo(Gender.Female);
    }
}
