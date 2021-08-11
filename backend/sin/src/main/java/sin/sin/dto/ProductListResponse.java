package sin.sin.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import sin.sin.domain.product.Status;
import sin.sin.domain.productCategory.ProductCategory;

import java.sql.Timestamp;

@Builder
@Getter
@ToString
public class ProductListResponse {
    private Long id;
    private String name;
    private String thumbnailName;
    private String thumbnailPath;
    private int price;
    private ProductCategory productCategory;
    private int discountPercent;
    private Timestamp createdDate;
    private Status status;
    private int reviewCount;


}
