package sin.sin.domain.coupon;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sin.sin.domain.couponList.CouponList;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="coupon_id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Timestamp expirationDate;

    @Column(nullable = false)
    private float discountPercent;

    @Column(nullable = false)
    private int discountAmount;

    @Column(nullable = false)
    private String code;

    @OneToMany(mappedBy = "coupon")
    private List<CouponList> couponLists = new ArrayList<>();
}
