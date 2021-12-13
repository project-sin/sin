package sin.sin.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sin.sin.domain.member.Member;
import sin.sin.domain.member.MemberRepository;
import sin.sin.dto.MemberResponse;
import sin.sin.handler.exception.NotExistsMemberException;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public MemberResponse findMember(Member member) {
        Member foundMember = memberRepository.findById(member.getId())
            .orElseThrow(() -> new NotExistsMemberException("해당되는 회원이 없습니다!"));

        return new MemberResponse(foundMember.getId(), foundMember.get_id(), foundMember.getName(),
            foundMember.getLevel().getSalePercent());
    }
}
