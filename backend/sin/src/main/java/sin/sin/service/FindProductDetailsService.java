package sin.sin.service;

import java.util.HashMap;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sin.sin.aws.AwsS3Config;
import sin.sin.aws.S3Util;
import sin.sin.domain.product.Product;
import sin.sin.domain.product.ProductDetails;
import sin.sin.domain.product.ProductRepository;
import sin.sin.dto.ProductDetailsResponse;

@Service
@RequiredArgsConstructor
public class FindProductDetailsService {

    private final ProductRepository productRepository;
    private final AwsS3Config awsS3Config;

    @Transactional
    public ProductDetailsResponse findProductDetailsService(String goodsNo) {
        Product product = productRepository.findProductByProductCode(goodsNo)
            .orElseThrow(() ->
                new IllegalArgumentException("goodsNo가 " + goodsNo + "에 해당되는 Product가 존재하지 않습니다."));

        return buildProductDetailsResponse(product);
    }

    private ProductDetailsResponse buildProductDetailsResponse(Product product) {
        HashMap<String, String> detailedInformation = findDetailedInformation(product.getProductDetails());
        String thumbnailUrl = getImageUrl(
            product.getProductCategory().getMainCategory(),
            product.getProductCategory().getSubCategory(),
            product.getProductCode());

        return ProductDetailsResponse.builder()
            .id(product.getId())
            .productName(product.getName())
            .contentSummary(product.getContentSummary())
            .thumbnailUrl(thumbnailUrl)
            .price(product.getPrice())
            .discountPercent(product.getDiscountPercent())
            .detailedInformation(detailedInformation)
            .build();
    }

    private String getImageUrl(String mainCategory, String subCategory, String productCode) {
        String fileName =
            "product/" + mainCategory + "/" + subCategory + "/" + productCode + ".jpg";

        S3Util s3Util = new S3Util(awsS3Config.amazonS3Client(), awsS3Config.getBucket());
        String imageUrl = s3Util.getFileURL(fileName);

        return imageUrl;
    }

    private HashMap<String, String> findDetailedInformation(ProductDetails productDetails) {
        HashMap<String, String> detailedInformation = new HashMap<String, String>();
        if (productDetails.getSaleUnit() != null) {
            detailedInformation.put("saleUnit", productDetails.getSaleUnit());
        }
        if (productDetails.getWeight() != null) {
            detailedInformation.put("weight", productDetails.getWeight());
        }
        if (productDetails.getDeliveryClassification() != null) {
            detailedInformation.put("deliveryClassification", productDetails.getDeliveryClassification());
        }
        if (productDetails.getPackingType() != null) {
            detailedInformation.put("packingType", productDetails.getPackingType());
        }
        if (productDetails.getOriginCountry() != null) {
            detailedInformation.put("originCountry", productDetails.getOriginCountry());
        }
        if (productDetails.getAllergicReaction() != null) {
            detailedInformation.put("allergicReaction", productDetails.getAllergicReaction());
        }
        if (productDetails.getExpirationDate() != null) {
            detailedInformation.put("expirationDate", productDetails.getExpirationDate());
        }

        return detailedInformation;
    }
}
