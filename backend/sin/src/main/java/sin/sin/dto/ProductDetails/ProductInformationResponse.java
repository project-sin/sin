package sin.sin.dto.ProductDetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
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

    public ProductInformationResponse(){
        this.saleUnit = null;
        this.weight = null;
        this.deliveryClassification = null;
        this.packingType = null;
        this.originCountry = null;
        this.allergicReaction = null;
        this.expirationDate = null;
    }
}
