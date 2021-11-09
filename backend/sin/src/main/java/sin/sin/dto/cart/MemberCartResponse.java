package sin.sin.dto.cart;

import com.querydsl.core.annotations.QueryProjection;
import lombok.*;
import sin.sin.domain.productCategory.ProductCategory;
import sin.sin.dto.ProductFrame;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
@Builder
@ToString
public class MemberCartResponse extends ProductFrame {
    private int cnt;

    @QueryProjection
    public MemberCartResponse(String name, String productCode, int price, ProductCategory productCategory, int discountPercent,int cnt) {
        super(name, productCode, price, productCategory, discountPercent);
        this.cnt = cnt;
    }
}



