package sin.sin.domain.freqQuestion;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FreqQuestionRepository extends JpaRepository<FreqQuestion, Long> {

    Page<FreqQuestion> findAll(Pageable pageable);
}
