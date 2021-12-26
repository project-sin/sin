package sin.sin.domain.coupon;

import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import sin.sin.domain.couponList.QCouponList;
import sin.sin.domain.member.QMember;
import sin.sin.dto.coupon.CouponResponse;
import sin.sin.dto.coupon.QCouponResponse;

@Repository
@RequiredArgsConstructor
public class FindCouponsRepository {

    private final JPAQueryFactory queryFactory;
    private final QMember member = QMember.member;
    private final QCoupon coupon = QCoupon.coupon;
    private final QCouponList couponList = QCouponList.couponList;

    public List<CouponResponse> findCouponListByMemberId(Long memberId) {

        return queryFactory.selectDistinct(new QCouponResponse(coupon.name, coupon.discountPercent,
                coupon.discountAmount,
                coupon.expirationDate,
                couponList.status
            )).from(member)
            .join(member.couponLists, couponList)
            .join(couponList.coupon, coupon)
            .where(member.id.eq(memberId))
            .fetch();
    }
}
