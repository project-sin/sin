package sin.sin.dto;

import java.util.HashMap;
import lombok.Builder;

@Builder
public class ProductDetailsResponse {

    private Long id;
    private String name;
    private String contentSummary;
    private String thumbnailName;
    private int price;
    private float discountPercent;
    private HashMap<String, String> detailedInformation;
}
