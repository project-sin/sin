package sin.sin.domain.orders;

import com.sun.istack.NotNull;
import java.sql.Timestamp;
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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import sin.sin.domain.coupon.Coupon;
import sin.sin.domain.member.Gender;
import sin.sin.domain.member.Member;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="orders_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "member_id")
    private Member member;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false, name = "delivery_status")
    @Enumerated(EnumType.STRING) // 이넘 이름을 DB에 저장
    private DeliveryStatus deliveryStatus;

    @CreationTimestamp
    private Timestamp orderedDate;
}
