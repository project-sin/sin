package sin.sin.dto;

import java.util.Map;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
public class ProductDetailsResponse {

    private Long id;
    private String name;
    private String contentSummary;
    private String thumbnailName;
    private int price;
    private float discountPercent;
    private Map<String, String> detailedInformation;
}
