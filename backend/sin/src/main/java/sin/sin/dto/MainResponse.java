package sin.sin.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import sin.sin.domain.product.Status;

@Setter
@Getter
@Builder
@ToString
public class MainResponse {
    private String name;
    private String productCode;
    private String imageUrl;
    private int price;
    private int discountPercent;
    private Status status;

}
