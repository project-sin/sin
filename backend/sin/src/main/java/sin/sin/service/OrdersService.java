package sin.sin.service;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sin.sin.domain.member.Member;
import sin.sin.domain.orders.OrderStatus;
import sin.sin.domain.orders.Orders;
import sin.sin.domain.orders.OrdersRepository;
import sin.sin.domain.product.Product;
import sin.sin.dto.order.OrderProductsResponse;
import sin.sin.dto.order.OrdersResponse;
import sin.sin.handler.exception.NotExistsMemberException;
import sin.sin.util.ImgUtil;

@Service
@RequiredArgsConstructor
public class OrdersService {

    private final OrdersRepository ordersRepository;

    private final ImgUtil imgUtil;

    @Transactional(readOnly = true)
    public List<OrdersResponse> findOrdersIds(Member member) {
        List<Orders> ordersList = ordersRepository.findOrdersByMemberId(member.getId());

        return ordersList.stream()
            .map((orders) -> {
                Product product = orders.getOrderProductLists().get(0).getProduct();
                return new OrdersResponse(
                    orders.getId(),
                    orders.getTotalPrice(),
                    orders.getOrderStatus(),
                    product.getName(),
                    orders.getOrderProductLists().stream().count(),
                    imgUtil.imgUrl(
                        product.getProductCategory().getMainCategory(),
                        product.getProductCategory().getSubCategory(),
                        product.getProductCode())
                );
            }).collect(Collectors.toList());
    }

    @Transactional
    public List<OrderProductsResponse> findProductsByOrderId(Long orderId, Member member) {
        if (!ordersRepository.checkMember(member.getId(), orderId)) {
            throw new NotExistsMemberException("회원님의 주문번호가 잘못되었습니다");
        }
        List<OrderProductsResponse> products = ordersRepository.findProductsByOrdersId(orderId);
        imgUtil.updateImgUrlProducts(products);

        return products;
    }
}
