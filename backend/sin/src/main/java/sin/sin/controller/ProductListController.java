package sin.sin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sin.sin.domain.product.Product;
import sin.sin.service.ProductListService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/shop/goods/goods_list")
public class ProductListController {
    private final ProductListService productListService;

    @GetMapping
    public ResponseEntity<?> newBestList(@RequestParam(value = "category", required = false) String category,
                                         @RequestParam(value = "list", required = false) String list) {
        //TODO : 객체 담아놓고 쓸지 결정
//        Map<String, String> map = new HashMap<>();
//        map.put("038", "신상품");
//        map.put("029", "베스트");

        //신상품 //이번달 출시 상품 조회
        if ("038".equals(category)) {
            List<Product> newProducts = productListService.newProductList();
            return ResponseEntity.ok().body(newProducts);
        }

        //알뜰쇼핑 //할인율 높은 상품 조회
        else if ("sale".equals(list)) {
            List<Product> cheapProducts = productListService.CheapProductList();
            return ResponseEntity.ok().body(cheapProducts);
        }

        //TODO : 베스트 상품 조회

        return ResponseEntity.badRequest().body("잘못된 요청입니다");
    }

}
