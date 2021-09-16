package sin.sin.domain.product;

import javax.persistence.Embeddable;
import lombok.Builder;
import lombok.Getter;

@Getter
@Embeddable
public class ProductDetails {

    private String saleUnit;

    private String weight;

    private String deliveryClassification;

    private String packingType;

    private String originCountry;

    private String allergicReaction;

    private String expirationDate;

    public ProductDetails() {
    }

    @Builder
    public ProductDetails(String saleUnit, String weight, String deliveryClassification,
        String packingType, String originCountry, String allergicReaction, String expirationDate) {
        this.saleUnit = saleUnit;
        this.weight = weight;
        this.deliveryClassification = deliveryClassification;
        this.packingType = packingType;
        this.originCountry = originCountry;
        this.allergicReaction = allergicReaction;
        this.expirationDate = expirationDate;
    }
}
