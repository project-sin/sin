package sin.sin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sin.sin.domain.product.Product;
import sin.sin.service.ProductListService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/shop/goods/goods_list")
public class ProductListController {
    private final ProductListService productListService;

    @GetMapping
    public ResponseEntity<?> newBestList(@RequestParam(value = "category") String category) {
        //TODO : 객체 담아놓고 쓸지 결정
//        Map<String, String> map = new HashMap<>();
//        map.put("038", "신상품");
//        map.put("029", "베스트");

        //신상품
        if (category.equals("038")) {
            List<Product> newProducts = productListService.newProductList();
            return ResponseEntity.ok().body(newProducts);
        }

        return ResponseEntity.badRequest().body("잘못된 요청입니다");
    }
}
