package sin.sin.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sin.sin.config.auth.CurrentUser;
import sin.sin.config.auth.PrincipalDetails;
import sin.sin.dto.EmoneyResponse;
import sin.sin.dto.address.AddressRequest;
import sin.sin.dto.address.AddressResponse;
import sin.sin.dto.address.EditAddressRequest;
import sin.sin.dto.address.AddressIdRequest;
import sin.sin.dto.order.OrderProductsResponse;
import sin.sin.dto.order.OrdersResponse;
import sin.sin.service.AddressService;
import sin.sin.service.EmoneyService;
import sin.sin.service.OrdersService;

@RestController
@RequestMapping("/mypage")
@RequiredArgsConstructor
public class MyPageController {

    private final OrdersService ordersService;

    private final AddressService addressService;

    private final EmoneyService emoneyService;

    @GetMapping("/orderList")
    public ResponseEntity<List<OrdersResponse>> findOrdersIds(
        @CurrentUser PrincipalDetails userDetails) {
        List<OrdersResponse> ordersResponseList = ordersService.findOrdersIds(
            userDetails.getMember());

        return ResponseEntity.ok().body(ordersResponseList);
    }

    @GetMapping("/orderview")
    public ResponseEntity<List<OrderProductsResponse>> findProductsByOrderId(
        @RequestParam("ordno") Long orderId,
        @CurrentUser PrincipalDetails userDetails) {
        List<OrderProductsResponse> responses = ordersService.findProductsByOrderId(orderId,
            userDetails.getMember());

        return ResponseEntity.ok().body(responses);
    }

    @GetMapping("/destination")
    public ResponseEntity<List<AddressResponse>> findAddresses(
        @CurrentUser PrincipalDetails userDetails) {
        List<AddressResponse> addresses = addressService.findAddresses(userDetails.getMember());

        return ResponseEntity.ok().body(addresses);
    }

    @PostMapping("/destination")
    public ResponseEntity<Void> addAddress(@RequestBody AddressRequest addressRequest,
        @CurrentUser PrincipalDetails userDetails) {
        addressService.addAddress(userDetails.getMember(), addressRequest);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/destination")
    public ResponseEntity<Void> removeAddress(@RequestBody AddressIdRequest request,
        @CurrentUser PrincipalDetails userDetails) {
        addressService.deleteAddress(userDetails.getMember(), request.getAddressId());

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/destination")
    public ResponseEntity<Void> editAddress(@RequestBody EditAddressRequest Request,
        @CurrentUser PrincipalDetails userDetails) {
        addressService.editAddress(userDetails.getMember(), Request);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/destination/select")
    public ResponseEntity<String> changeSelectedAddress(@RequestBody AddressIdRequest request,
        @CurrentUser PrincipalDetails userDetails) {
        addressService.changeSelectedAddress(userDetails.getMember(), request.getAddressId());

        return ResponseEntity.ok().body("배송지 선택이 완료되었습니다");
    }

    @GetMapping("/emoney")
    public ResponseEntity<List<EmoneyResponse>> findEmoneyList(
        @CurrentUser PrincipalDetails userDetails) {

        return ResponseEntity.ok().body(emoneyService.findEmoneyList(userDetails.getMember()));
    }
}