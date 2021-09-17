package sin.sin.dto.ProductDetails;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ProductInformationResponse {

    private String saleUnit;
    private String weight;
    private String deliveryClassification;
    private String packingType;
    private String originCountry;
    private String allergicReaction;
    private String expirationDate;
}
