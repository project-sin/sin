package sin.sin.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import sin.sin.domain.productCategory.ProductCategory;

@NoArgsConstructor
@Getter
@ToString
public class ProductFrame {
    private String name;
    private String productCode;
    private String imageUrl;
    private int price;
    private ProductCategory productCategory;
    private int discountPercent;

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @QueryProjection
    public ProductFrame(String name, String productCode, int price, ProductCategory productCategory, int discountPercent) {
        this.name = name;
        this.productCode = productCode;
        this.price = price;
        this.productCategory = productCategory;
        this.discountPercent = discountPercent;
    }
}
