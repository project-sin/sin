package sin.sin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sin.sin.dto.ProductDetails.ProductQnaResponse;
import sin.sin.service.ProductQnaService;

@RestController
@RequestMapping("/goods/qna")
@RequiredArgsConstructor
public class ProductQnaController {

    private final ProductQnaService productQnaService;

    @GetMapping
    public ResponseEntity<Page<ProductQnaResponse>> findProductDetailsQna(@RequestParam("goodsno") String goodsNo, @RequestParam(value = "page", defaultValue = "1") int page) {

        return ResponseEntity.ok()
                .body(productQnaService.findProductQna(goodsNo, page));
    }
}
