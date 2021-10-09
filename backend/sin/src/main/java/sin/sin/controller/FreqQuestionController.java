package sin.sin.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sin.sin.dto.FreqQuestionResponse;
import sin.sin.service.FreqQuestionService;

@RestController
@RequestMapping("/service/faq")
@RequiredArgsConstructor
public class FreqQuestionController {

    private final FreqQuestionService freqQuestionService;

    @GetMapping
    public ResponseEntity<List<FreqQuestionResponse>> findAllFaq(@RequestParam(value="page", defaultValue = "1") int page){

        return ResponseEntity.ok().body(freqQuestionService.findAllFaq(page));
    }

    @GetMapping("/total")
    public ResponseEntity<Integer> findTotal(){
        return ResponseEntity.ok().body(freqQuestionService.findTotal());
    }
}
