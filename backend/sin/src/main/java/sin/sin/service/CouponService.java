package sin.sin.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sin.sin.domain.coupon.Coupon;
import sin.sin.domain.coupon.CouponRepository;
import sin.sin.domain.couponList.CouponList;
import sin.sin.domain.couponList.CouponListRepository;
import sin.sin.domain.couponList.Status;
import sin.sin.domain.member.Member;
import sin.sin.handler.exception.NotExistsCouponException;

@Service
@RequiredArgsConstructor
public class CouponService {

    private final CouponRepository couponRepository;
    private final CouponListRepository couponListRepository;

    @Transactional
    public void addCoupon(Member member, String code) {
        Coupon coupon = couponRepository.findByCode(code).orElseThrow(
            () -> new NotExistsCouponException("코드에 해당되는 쿠폰은 존재하지 않습니다.")
        );
        CouponList couponList = CouponList.builder()
            .member(member)
            .status(Status.NonUse).build();
        couponList.addCoupon(coupon);

        couponListRepository.save(couponList);
    }
}
