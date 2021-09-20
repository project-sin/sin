package sin.sin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sin.sin.domain.product.Product;
import sin.sin.dto.ProductListResponse;
import sin.sin.service.ProductListService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/goods/goods_list")
public class ProductListController {
    private final ProductListService productListService;

    @GetMapping
    public ResponseEntity<?> newBestList(@RequestParam(value = "category", required = false) String category,
                                         @RequestParam(value = "list", required = false) String list) {

        //신상품 //이번달 출시 상품 & 가장 최근 등록 상품순 조회
        if ("038".equals(category)) {
            List<ProductListResponse> newProducts = productListService.newProductList();
            return ResponseEntity.ok().body(newProducts);
        }

        //베스트상품 //후기 많은 상품순 조회
        else if("029".equals(category)){
            List<ProductListResponse> bestProducts = productListService.bestProductList();
            return ResponseEntity.ok().body(bestProducts);
        }

        //알뜰쇼핑 //할인율 높은 상품순 조회
        else if ("sale".equals(list)) {
            List<ProductListResponse> cheapProducts = productListService.cheapProductList();
            return ResponseEntity.ok().body(cheapProducts);
        }

        //카테고리별 조회
        else{
            List<ProductListResponse> categoryProducts = productListService.categoryProductList(category);
            return ResponseEntity.ok().body(categoryProducts);
        }

    }

}
