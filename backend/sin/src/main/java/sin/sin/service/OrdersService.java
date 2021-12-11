package sin.sin.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sin.sin.domain.member.Member;
import sin.sin.domain.orders.OrdersRepository;
import sin.sin.dto.order.OrdersResponse;

@Service
@RequiredArgsConstructor
public class OrdersService {

    private final OrdersRepository ordersRepository;

    //todo: repository 에 너무 의존적...괜찮나??
    @Transactional(readOnly = true)
    public List<OrdersResponse> findOrdersIds(Member member) {

        return ordersRepository.findOrdersByMemberId(member.getId());
    }
}
