package sin.sin.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sin.sin.domain.coupon.Coupon;
import sin.sin.domain.couponList.CouponList;
import sin.sin.domain.couponList.CouponListRepository;
import sin.sin.domain.member.Member;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class CouponServiceTest {

    @Autowired
    private CouponService couponService;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private CouponListRepository couponListRepository;

    Member member;

    @BeforeEach
    void setup() {
        member = entityManager.find(Member.class, 1L);
    }

    @Test
    void addCoupon() {
        //given
        Coupon coupon = entityManager.find(Coupon.class, 1L);

        //when
        couponService.addCoupon(member, coupon.getCode());
        entityManager.flush();
        entityManager.clear();

        //then
        List<CouponList> couponLists = couponListRepository.findAll();
        assertThat(couponLists.size()).isEqualTo(1L);
        assertThat(couponLists.get(0).getCoupon().getId()).isEqualTo(coupon.getId());
        assertThat(couponLists.get(0).getMember().getId()).isEqualTo(member.getId());
    }

}
