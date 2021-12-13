package sin.sin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sin.sin.dto.productReview.ProductReviewResponse;
import sin.sin.service.ProductReviewService;

@RestController
@RequestMapping("/goods/review")
@RequiredArgsConstructor
public class ProductReviewController {

    private final ProductReviewService productReivewService;

    @GetMapping
    public ResponseEntity<Page<ProductReviewResponse>> findProductDetailsReview(@RequestParam("goodsno") String goodsNo, @RequestParam(value = "page", defaultValue = "1") int page) {

        return ResponseEntity.ok()
                .body(productReivewService.findProductReview(goodsNo, page));
    }
}
