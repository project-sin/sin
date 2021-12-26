package sin.sin.service;

import java.sql.Timestamp;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sin.sin.domain.emoney.Emoney;
import sin.sin.domain.emoney.EmoneyRepository;
import sin.sin.domain.event.Event;
import sin.sin.domain.event.EventRepository;
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
    private final EventRepository eventRepository;
    private final EmoneyRepository emoneyRepository;

    @Transactional
    public JoinResponse join(JoinRequest joinRequest) throws IOException {
        isDuplicateId(joinRequest.getId());
        isDuplicateEmail(joinRequest.getEmail());

        joinRequest.setPassword(passwordEncoder.encode(joinRequest.getPassword()));
        Level firstLevel = levelRepository.findFirstByOrderBySalePercentAsc();
        joinRequest.setLevel(firstLevel);

        Member member = joinRequest.toMemberEntity();
        memberRepository.save(member);

        String referral_id = joinRequest.getReferral_id();
        String event = joinRequest.getEvent();
        if (referral_id != null) {
            //from member, 1000
            Member referralMember = memberRepository.findBy_id(referral_id).get();
            emoneyRepository.save(emoneyBuilder(referralMember, "추천인 적립금", 1000));

            //to member, 1000
            emoneyRepository.save(emoneyBuilder(member, "추천인 적립금", 1000));

        } else if (event != null) {
            emoneyRepository.save(emoneyBuilder(member, "이벤트 적립금", 1000));
        }

        return new JoinResponse(joinRequest.getName());
    }

    @Transactional(readOnly = true)
    public Boolean existedReferralId(String referral_id) {
        Optional<Member> findMember = memberRepository.findBy_id(referral_id);
        if (findMember.isPresent()) {
            return true;
        }
        return false;
    }

    @Transactional(readOnly = true)
    public Boolean existedEvent(String event) {
        Optional<Event> findEvent = eventRepository.findByFileCode(event);
        if (findEvent.isPresent()) {
            return true;
        }
        return false;
    }

    @Transactional(readOnly = true)
    public String login(LoginRequest loginRequest) {
        Authentication authentication = matchIdAndPassword(loginRequest);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return tokenProvider.createToken(authentication);
    }

//    @Transactional(readOnly = true)
//    public List<Member> findMembers() {
//        return memberRepository.findAll();
//    }

    @Transactional(readOnly = true)
    public void isDuplicateId(String id) {
        Optional<Member> findMember = memberRepository.findBy_id(id);
        if (findMember.isPresent()) {
            throw new AlreadyExistedIdException("이미 사용중인 아이디입니다.");
        }
    }

    @Transactional(readOnly = true)
    public void isDuplicateEmail(String email) {
        Optional<Member> findMember = memberRepository.findByEmail(email);
        if (findMember.isPresent()) {
            throw new AlreadyExistedEmailException("이미 사용중인 이메일입니다.");
        }
    }

    private Authentication matchIdAndPassword(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginRequest.getId(),
                loginRequest.getPassword()
            ));
        return authentication;
    }

    private Emoney emoneyBuilder(Member member, String content, int point) {
        Timestamp stamp = new Timestamp(System.currentTimeMillis());
        stamp.setYear(stamp.getYear() + 2);
        return Emoney.builder()
            .member(member)
            .content(content)
            .point(point)
            .expirationDate(stamp).build();
    }
}
