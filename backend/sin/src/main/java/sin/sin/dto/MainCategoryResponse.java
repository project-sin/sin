package sin.sin.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import sin.sin.domain.product.Status;

public interface MainCategoryResponse{
    String getName();
    String getProductCode();
    int getPrice();
    int getDiscountPercent();
    Status getStatus();
    String getMainCategory();
    String getSubCategory();
}
