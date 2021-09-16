package sin.sin.dto.ProductDetails;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class ProductDetailsResponse {

    private Long id;
    private String productName;
    private String contentSummary;
    private String thumbnailUrl;
    private int price;
    private int discountPercent;
    private ProductInformationResponse productInformationResponse;
}
