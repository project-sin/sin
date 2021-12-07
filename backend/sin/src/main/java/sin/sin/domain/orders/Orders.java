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
import sin.sin.domain.orderProductList.OrderProductList;

@Entity
@NoArgsConstructor
@Getter
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orders_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "order_list_id")
    private OrderList orderList;

    @Column(nullable = false)
    private String address;

    @OneToMany(mappedBy = "orders")
    private List<OrderProductList> orderProductLists = new ArrayList<>();

    @Column(nullable = false, name = "delivery_status")
    @Enumerated(EnumType.STRING) // 이넘 이름을 DB에 저장
    private DeliveryStatus deliveryStatus;

    @CreationTimestamp
    private Timestamp orderedDate;

    @Builder
    public Orders(Long id, Member member, OrderList orderList, String address,
        List<OrderProductList> orderProductLists, DeliveryStatus deliveryStatus,
        Timestamp orderedDate) {
        this.id = id;
        this.member = member;
        this.orderList = orderList;
        this.address = address;
        if (Objects.nonNull(orderProductLists)) {
            this.orderProductLists = orderProductLists;
        }
        this.deliveryStatus = deliveryStatus;
        this.orderedDate = orderedDate;
    }
}
