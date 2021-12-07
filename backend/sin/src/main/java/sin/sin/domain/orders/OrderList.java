package sin.sin.domain.orders;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import sin.sin.domain.member.Member;
import sin.sin.handler.exception.WrongInputException;

@Entity
@NoArgsConstructor
@Getter
public class OrderList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_list_id", nullable = false)
    private Long id;

    @Column(nullable = false, name = "order_status")
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @OneToMany(mappedBy = "orderList")
    private List<Orders> orders = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "member_id")
    private Member member;

    @Column(nullable = false)
    private int totalCount;

    @CreationTimestamp
    private Timestamp orderedDate;

    @Builder
    public OrderList(Long id, OrderStatus orderStatus, List<Orders> orders, Member member,
        int totalCount, Timestamp orderedDate) {
        validate(orderStatus, member);
        this.id = id;
        this.orderStatus = orderStatus;
        this.member = member;
        this.totalCount = totalCount;
        this.orderedDate = orderedDate;
        if (Objects.nonNull(orders)) {
            this.orders = orders;
        }
    }

    private void validate(OrderStatus orderStatus, Member member) {
        if (Objects.isNull(orderStatus)) {
            throw new WrongInputException("주문상태가 존재해야 합니다");
        }
        if (Objects.isNull(member) || Objects.isNull(member.getId())) {
            throw new WrongInputException("유저가 존재해야 합니다");
        }
    }
}
