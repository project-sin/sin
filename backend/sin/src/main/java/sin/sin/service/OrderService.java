/*
package sin.sin.service;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sin.sin.domain.member.Member;
import sin.sin.domain.orders.Orders;
import sin.sin.domain.product.Product;
import sin.sin.dto.order.OrdersResponse;
import sin.sin.util.ImgUtil;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final Orders2Repository ordersRepository;

    private final ImgUtil imgUtil;

    @Transactional(readOnly = true)
    public List<OrdersResponse> findOrdersIds(Member member) {
        List<Orders> ordersList = ordersRepository.findAllByMemberId(member.getId());

        return ordersList.stream()
            .map((orders) -> {
                Product product = orders.getOrderProductLists().get(0).getProduct();
                return new OrdersResponse(
                    orders.getId(),
                    orders.getTotalPrice(),
                    orders.getOrderStatus(),
                    imgUtil.imgUrl(
                        product.getProductCategory().getMainCategory(),
                        product.getProductCategory().getSubCategory(),
                        product.getProductCode())
                );
            })
            .collect(Collectors.toList());
    }
}
*/
