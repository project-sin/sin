package sin.sin.dto.coupon;

import com.querydsl.core.annotations.QueryProjection;
import java.util.Date;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sin.sin.domain.couponList.Status;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class CouponResponse {

    private String name;
    private int discountPercent;
    private int discountAmount;
    private Date expirationDate;
    private Status status;

    @QueryProjection
    public CouponResponse(String name, int discountPercent, int discountAmount,
        Date expirationDate, Status status) {
        this.name = name;
        this.discountPercent = discountPercent;
        this.discountAmount = discountAmount;
        this.expirationDate = expirationDate;
        this.status = status;
    }
}
