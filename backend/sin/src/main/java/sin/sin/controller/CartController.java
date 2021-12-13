package sin.sin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import sin.sin.config.auth.PrincipalDetails;
import sin.sin.dto.ProductFrame;
import sin.sin.dto.cart.CartRequest;
import sin.sin.dto.cart.MemberCartResponse;
import sin.sin.service.CartService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/goods")
public class CartController {

    private final CartService cartService;

    @PostMapping("/cart")
    ResponseEntity<String> addCart(@RequestBody CartRequest cartRequest, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        Long id = principalDetails.getMember().getId();
//        System.out.println(principalDetails.getMember());
//        System.out.println(principalDetails.getMember().getPassword());

        cartService.add(id, cartRequest);

        return ResponseEntity.ok("장바구니 내역을 저장했습니다.");
    }

    //리스트 삭제 가능
    @DeleteMapping("/cart")
    ResponseEntity<String> deleteCart(@RequestParam("productCode") List<String> productCodeList, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        Long id = principalDetails.getMember().getId();

        cartService.delete(id, productCodeList);

        return ResponseEntity.ok("장바구니 내역이 삭제됐습니다.");
    }

    @GetMapping("/cart/member")
    ResponseEntity<List<MemberCartResponse>> showMemberCart(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        Long id = principalDetails.getMember().getId();
        return ResponseEntity.ok(cartService.selectMemberCart(id));
    }

    @GetMapping("/cart")
    ResponseEntity<List<ProductFrame>> showNonMemberCart(@RequestParam("productCode") List<String> productCodeList) {
        return ResponseEntity.ok(cartService.findProductDetailByProductCode(productCodeList));
    }
}
