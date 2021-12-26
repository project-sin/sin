package sin.sin.domain.emoney;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmoneyRepository extends JpaRepository<Emoney, Long> {

    List<Emoney> findAllByMemberId(Long memberId);
}