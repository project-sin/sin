package sin.sin.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sin.sin.domain.freqQuestion.FreqQuestion;
import sin.sin.domain.freqQuestion.FreqQuestionRepository;
import sin.sin.dto.FreqQuestionResponse;

@Service
@RequiredArgsConstructor
public class FreqQuestionService {

    private final FreqQuestionRepository freqQuestionRepository;

    @Transactional
    public List<FreqQuestionResponse> findAllFaq(int page) {
        Pageable sortedByDate = PageRequest.of(page-1,10, Sort.by("id").descending());

        Page<FreqQuestion> result = freqQuestionRepository.findAll(sortedByDate);

        return result.getContent().stream()
            .map(freqQuestion -> new FreqQuestionResponse(
                freqQuestion.getId(),
                freqQuestion.getCategory(),
                freqQuestion.getTitle(),
                freqQuestion.getContent()
            ))
            .collect(Collectors.toList());
    }

    @Transactional
    public int findTotal() {

        List<FreqQuestion> freqQuestions = freqQuestionRepository.findAll();
        return freqQuestions.size();
    }
}
