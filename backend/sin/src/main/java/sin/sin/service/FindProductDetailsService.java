package sin.sin.service;

import java.util.HashMap;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sin.sin.aws.AwsS3Config;
import sin.sin.aws.S3Util;
import sin.sin.domain.product.Product;
import sin.sin.domain.product.ProductRespository;
import sin.sin.dto.ProductDetailsResponse;

@Service
@RequiredArgsConstructor
public class FindProductDetailsService {

    private final ProductRespository productRespository;
    private final AwsS3Config awsS3Config;

    @Transactional
    public ProductDetailsResponse findProductDetailsService(Long productId) {
        Product product = productRespository.findById(productId)
            .orElseThrow(() ->
                new IllegalArgumentException("id가 " + productId + "에 해당되는 Product가 존재하지 않습니다."));

        return buildProductDetailsResponse(product);
    }

    private ProductDetailsResponse buildProductDetailsResponse(Product product) {
        HashMap<String, String> detailedInformation = findDetailedInformation(product);
        String thumbnailUrl = getImageUrl(product.getThumbnailName());

        return ProductDetailsResponse.builder()
            .id(product.getId())
            .productName(product.getProductName())
            .contentSummary(product.getContentSummary())
            .thumbnailUrl(thumbnailUrl)
            .price(product.getPrice())
            .discountPercent(product.getDiscountPercent())
            .detailedInformation(detailedInformation)
            .build();
    }

    private String getImageUrl(String fileName){
        S3Util s3Util = new S3Util(awsS3Config.amazonS3Client(), awsS3Config.getBucket());
        String imageUrl = s3Util.getFileURL(fileName);

        return imageUrl;
    }

    private HashMap<String, String> findDetailedInformation(Product product) {
        HashMap<String, String> detailedInformation = new HashMap<String, String>();
        if (product.getSaleUnit() != null) {
            detailedInformation.put("saleUnit", product.getSaleUnit());
        }
        if (product.getWeight() != null) {
            detailedInformation.put("weight", product.getWeight());
        }
        if (product.getDeliveryClassification() != null) {
            detailedInformation.put("deliveryClassification", product.getDeliveryClassification());
        }
        if (product.getPackingType() != null) {
            detailedInformation.put("packingType", product.getPackingType());
        }
        if (product.getOriginCountry() != null) {
            detailedInformation.put("originCountry", product.getOriginCountry());
        }
        if (product.getAllergicReaction() != null) {
            detailedInformation.put("allergicReaction", product.getAllergicReaction());
        }
        if (product.getExpirationDate() != null) {
            detailedInformation.put("expirationDate", product.getExpirationDate());
        }

        return detailedInformation;
    }
}
