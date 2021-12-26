package sin.sin.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sin.sin.domain.emoney.EmoneyRepository;
import sin.sin.domain.member.Member;
import sin.sin.dto.EmoneyResponse;

@Service
@RequiredArgsConstructor
public class EmoneyService {

    private final EmoneyRepository emoneyRepository;

    @Transactional(readOnly = true)
    public List<EmoneyResponse> findEmoneyList(Member member) {

        return emoneyRepository.findAllByMemberId(member.getId()).stream()
            .map((emoney) -> new EmoneyResponse(emoney.getCreatedDate(), emoney.getExpirationDate(),
                emoney.getContent(), emoney.getPoint()))
            .collect(Collectors.toList());
    }
}
