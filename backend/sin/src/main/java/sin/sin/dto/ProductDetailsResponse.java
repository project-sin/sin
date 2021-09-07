package sin.sin.dto;

import java.util.HashMap;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ProductDetailsResponse {

    private Long id;
    private String productName;
    private String contentSummary;
    private String thumbnailUrl;
    private int price;
    private float discountPercent;
    private HashMap<String, String> detailedInformation;
}
