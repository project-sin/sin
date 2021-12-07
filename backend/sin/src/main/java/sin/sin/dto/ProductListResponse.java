package sin.sin.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.*;
import sin.sin.domain.product.Status;
import sin.sin.domain.productCategory.ProductCategory;

import java.sql.Timestamp;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ProductListResponse extends ProductFrame{
    private String contentSummary;
    private Timestamp createdDate;
    private Status status;
    private int reviewCount;

    @QueryProjection
    public ProductListResponse(String name, String productCode, int price, ProductCategory productCategory, int discountPercent,
                               String contentSummary, Timestamp createdDate, Status status, int reviewCount) {
        super(name, productCode, price, productCategory, discountPercent);
        this.contentSummary = contentSummary;
        this.createdDate = createdDate;
        this.status = status;
        this.reviewCount = reviewCount;
    }

}
