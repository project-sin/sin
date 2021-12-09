package sin.sin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sin.sin.dto.ProductDetails.ProductDetailsResponse;
import sin.sin.dto.ProductDetails.ProductQnaResponse;
import sin.sin.service.FindProductDetailsService;

@RestController
@RequestMapping("/goods/goods_view")
@RequiredArgsConstructor
public class FindProductDetailsController {

    private final FindProductDetailsService findProductDetailsService;

    @GetMapping
    public ResponseEntity<ProductDetailsResponse> findProductDetails(@RequestParam("goodsno") String goodsNo) {

        return ResponseEntity.ok()
                .body(findProductDetailsService.findProductDetailsService(goodsNo));
    }

    @GetMapping("/desc")
    public ResponseEntity<String> findProductDetailsDescription(@RequestParam("goodsno") String goodsNo) {

        return ResponseEntity.ok()
                .body(findProductDetailsService.findProductDetailsDesc(goodsNo));
    }

    @GetMapping("/info")
    public ResponseEntity<String> findProductDetailsInformation(@RequestParam("goodsno") String goodsNo) {

        return ResponseEntity.ok()
                .body(findProductDetailsService.findProductDetailsInfo(goodsNo));
    }

    @GetMapping("/qna")
    public ResponseEntity<Page<ProductQnaResponse>> findProductDetailsQna(@RequestParam("goodsno") String goodsNo, @RequestParam(value = "page", defaultValue = "1") int page) {

        return ResponseEntity.ok()
                .body(findProductDetailsService.findProductDetailsQna(goodsNo, page));
    }

}
