package sin.sin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import sin.sin.config.auth.PrincipalDetails;
import sin.sin.handler.exception.RequestLoginException;
import sin.sin.service.CartService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/goods")
public class CartController {

    private final CartService cartService;

    @PostMapping("/cart") //+
    ResponseEntity<String> addCart(String productCode, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        Long id = principalDetails.getMember().getId();

        if (id==null) {
            throw new RequestLoginException("로그인이 필요한 작업입니다.");
        }

        cartService.add(id, productCode);

        return ResponseEntity.ok("장바구니 내역이 성공적으로 저장했습니다.");
    }


}
