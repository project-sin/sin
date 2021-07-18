package sin.sin.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;
import javax.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sin.sin.domain.freqQuestion.FreqQuestion;
import sin.sin.domain.freqQuestion.FreqQuestionRepository;
import sin.sin.dto.FreqQuestionResponse;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
class FreqQuestionServiceTest {

    @Autowired
    private FreqQuestionRepository freqQuestionRepository;

    @Autowired
    private FreqQuestionService freqQuestionService;

    @Test
    void findAllFaq() {
        //given
        for(int i=0;i<24;i++){
            FreqQuestion freqQuestion=FreqQuestion.builder()
                .category("테스트카테고리")
                .title("문제번호"+i)
                .content("이러이러한 문제때문에 그런 문제가 발샐할 수 있습니다")
                .build();
            freqQuestionRepository.save(freqQuestion);
        }

        //when
        List<FreqQuestionResponse> faqs1= freqQuestionService.findAllFaq(1);
        List<FreqQuestionResponse> faqs2= freqQuestionService.findAllFaq(2);
        List<FreqQuestionResponse> faqs3= freqQuestionService.findAllFaq(3);

        //then
        assertThat(faqs1.size()).isEqualTo(10);
        assertThat(faqs2.size()).isEqualTo(10);
        assertThat(faqs3.size()).isEqualTo(4);
        assertThat(faqs1.get(0).getId()).isEqualTo(24);
        assertThat(faqs2.get(0).getId()).isEqualTo(14);
        assertThat(faqs3.get(0).getId()).isEqualTo(4);
    }
}