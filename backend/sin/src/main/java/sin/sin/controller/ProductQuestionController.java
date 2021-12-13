package sin.sin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sin.sin.config.auth.CurrentUser;
import sin.sin.config.auth.PrincipalDetails;
import sin.sin.dto.productQna.ProductQnaResponse;
import sin.sin.dto.productQna.ProductQuestionRequest;
import sin.sin.service.ProductQuestionService;

@RestController
@RequestMapping("/goods/qna")
@RequiredArgsConstructor
public class ProductQuestionController {

    private final ProductQuestionService productQuestionService;

    @GetMapping
    public ResponseEntity<Page<ProductQnaResponse>> findProductQuestion(@RequestParam("goodsno") String goodsNo, @RequestParam(value = "page", defaultValue = "1") int page) {
        return ResponseEntity.ok()
                .body(productQuestionService.findProductQna(goodsNo, page));
    }

    @PostMapping
    public ResponseEntity insertProductQuestion(@RequestBody ProductQuestionRequest productQuestionRequest, @CurrentUser PrincipalDetails userDetails) {

        productQuestionService.insertProductQuestion(productQuestionRequest, userDetails.getMember());

        return ResponseEntity.ok()
                .body("상품이 등록됐습니다.");
    }
}
