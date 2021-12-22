package sin.sin.domain.coupon;

import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import sin.sin.domain.couponList.QCouponList;

@Repository
@RequiredArgsConstructor
public class FindCouponsRepository {

    private final JPAQueryFactory queryFactory;
    private final QCoupon coupon = QCoupon.coupon;
    private final QCouponList couponList = QCouponList.couponList;

    public List<Coupon> findCouponListByMemberId(Long memberId) {

        return queryFactory.selectDistinct(coupon).from(coupon)
            .join(coupon.couponLists, couponList)
            .where(couponList.member.id.eq(memberId))
            .fetch();
    }
}
