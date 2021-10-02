package sin.sin.dto.ProductMain;

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
