package sin.sin.domain.product;

import java.util.Objects;
import javax.persistence.Embeddable;
import lombok.Builder;
import lombok.Setter;

@Setter
@Embeddable
public class ProductDetails {

    private String saleUnit;

    private String weight;

    private String deliveryClassification;

    private String packingType;

    private String originCountry;

    private String allergicReaction;

    private String expirationDate;

    public String getSaleUnit() {
        if(Objects.isNull(saleUnit) || saleUnit.trim().isEmpty())
            return null;
        return saleUnit;
    }

    public String getWeight() {
        if(Objects.isNull(weight) || weight.trim().isEmpty())
            return null;
        return weight;
    }

    public String getDeliveryClassification() {
        if(Objects.isNull(deliveryClassification) || deliveryClassification.trim().isEmpty())
            return null;
        return deliveryClassification;
    }

    public String getPackingType() {
        if(Objects.isNull(packingType) || packingType.trim().isEmpty())
            return null;
        return packingType;
    }

    public String getOriginCountry() {
        if(Objects.isNull(originCountry) || originCountry.trim().isEmpty())
            return null;
        return originCountry;
    }

    public String getAllergicReaction() {
        if(Objects.isNull(allergicReaction) || allergicReaction.trim().isEmpty())
            return null;
        return allergicReaction;
    }

    public String getExpirationDate() {
        if(Objects.isNull(expirationDate) || expirationDate.trim().isEmpty())
            return null;
        return expirationDate;
    }

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
