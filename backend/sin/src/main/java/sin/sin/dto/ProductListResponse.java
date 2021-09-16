package sin.sin.dto;

import lombok.*;
import sin.sin.domain.product.Status;
import sin.sin.domain.productCategory.ProductCategory;

import java.sql.Timestamp;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductListResponse {
    private String name;
    private String productCode;
    private String imageUrl;
    private int price;
    private ProductCategory productCategory;
    private int discountPercent;
    private Timestamp createdDate;
    private Status status;
    private int reviewCount;

}
