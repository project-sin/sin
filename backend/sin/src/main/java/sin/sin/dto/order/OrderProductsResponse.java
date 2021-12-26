package sin.sin.dto.order;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import sin.sin.domain.productCategory.ProductCategory;
import sin.sin.dto.ProductFrame;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class OrderProductsResponse extends ProductFrame {

    private int count;

    @QueryProjection
    public OrderProductsResponse(String name, String productCode, int price,
        ProductCategory productCategory, int discountPercent,
        int count) {
        super(name, productCode, price, productCategory, discountPercent);
        this.count = count;
    }
}
