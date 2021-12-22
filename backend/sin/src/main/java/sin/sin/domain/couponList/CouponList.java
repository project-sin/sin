package sin.sin.domain.couponList;

import com.sun.istack.NotNull;
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
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sin.sin.domain.coupon.Coupon;
import sin.sin.domain.member.Member;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class CouponList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="coupon_list_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "coupon_id")
    private Coupon coupon;

    @NotNull
    @Enumerated(EnumType.STRING) // 이넘 이름을 DB에 저장
    private Status status;

    public void addCoupon(Coupon coupon){
        if(this.coupon !=null){
            this.coupon.getCouponLists().remove(this);
        }
        this.coupon = coupon;
        coupon.getCouponLists().add(this);
    }
}
