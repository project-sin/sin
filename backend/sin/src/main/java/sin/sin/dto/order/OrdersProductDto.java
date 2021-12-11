package sin.sin.dto.order;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class OrdersProductDto {

    private Long productId;
    private String productCode;
    private String productName;
    private String mainCategory;
    private String subCategory;

    @QueryProjection
    public OrdersProductDto(Long productId, String productCode, String productName, String mainCategory,
        String subCategory) {
        this.productId = productId;
        this.productCode = productCode;
        this.productName = productName;
        this.mainCategory = mainCategory;
        this.subCategory = subCategory;
    }

}
