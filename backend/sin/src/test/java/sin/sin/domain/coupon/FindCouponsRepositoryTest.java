package sin.sin.domain.coupon;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import sin.sin.domain.couponList.CouponList;
import sin.sin.domain.couponList.Status;
import sin.sin.domain.member.Member;
import sin.sin.dto.coupon.CouponResponse;

@Transactional(readOnly = true)
@SpringBootTest
public class FindCouponsRepositoryTest {

    @Autowired
    private FindCouponsRepository findCouponsRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    void findCouponListByMemberId() {
        //given
        Coupon coupon = entityManager.find(Coupon.class, 1L);
        Member member = entityManager.find(Member.class, 1L);
        CouponList couponList = CouponList.builder()
            .coupon(coupon)
            .status(Status.NonUse).build();
        couponList.addMember(member);

        entityManager.persist(couponList);
        entityManager.flush();
        entityManager.clear();

        //when
        List<CouponResponse> couponResponses = findCouponsRepository.findCouponListByMemberId(
            member.getId());

        //then
        assertThat(couponResponses.size()).isEqualTo(1);
        assertThat(couponResponses.get(0).getName()).isEqualTo(coupon.getName());
        assertThat(couponResponses.get(0).getStatus()).isEqualTo(couponList.getStatus());
    }
}
