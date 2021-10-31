package sin.sin.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sin.sin.domain.level.Level;
import sin.sin.domain.level.LevelRepository;
import sin.sin.domain.member.Member;
import sin.sin.domain.member.MemberRepository;
import sin.sin.dto.auth.JoinRequest;
import sin.sin.dto.auth.JoinResponse;
import sin.sin.dto.auth.LoginRequest;
import sin.sin.handler.exception.AlreadyExistedEmailException;
import sin.sin.handler.exception.AlreadyExistedIdException;
import sin.sin.security.jwt.TokenProvider;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

//회원가입
@Service
@Log4j2
@RequiredArgsConstructor
public class AuthService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;
    private final LevelRepository levelRepository;

    @Transactional
    public JoinResponse join(JoinRequest joinRequest) throws IOException {
        isDuplicateId(joinRequest.getId());
        isDuplicateEmail(joinRequest.getEmail());

        joinRequest.setPassword(passwordEncoder.encode(joinRequest.getPassword()));

        Level firstLevel = levelRepository.findFirstByOrderBySalePercentAsc();
        joinRequest.setLevel(firstLevel);

        Member member = joinRequest.toMemberEntity();
        memberRepository.save(member);

        return new JoinResponse(joinRequest.getName());
    }

    @Transactional(readOnly = true)
    public String login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getId(),
                        loginRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return tokenProvider.createToken(authentication);
    }


    @Transactional(readOnly = true)
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public void isDuplicateId(String id) {
        Optional<Member> findMember = memberRepository.findBy_id(id);
        if (findMember.isPresent()) {
            throw new AlreadyExistedIdException("이미 사용중인 아이디입니다.");
        }
    }

    public void isDuplicateEmail(String email) {
        Optional<Member> findMember = memberRepository.findByEmail(email);
        if (findMember.isPresent()) {
            throw new AlreadyExistedEmailException("이미 사용중인 이메일입니다.");
        }
    }

}
