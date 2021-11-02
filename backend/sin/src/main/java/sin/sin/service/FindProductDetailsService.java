package sin.sin.service;

import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sin.sin.aws.AwsS3Config;
import sin.sin.aws.S3Util;
import sin.sin.domain.product.Product;
import sin.sin.domain.product.ProductDetails;
import sin.sin.domain.product.ProductRepository;
import sin.sin.dto.ProductDetails.ProductDetailsResponse;
import sin.sin.dto.ProductDetails.ProductInformationResponse;

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
            .productInformationResponse(findDetailedInformation(product.getProductDetails()))
            .build();
    }

    private String getImageUrl(String mainCategory, String subCategory, String productCode) {
        String fileName =
            "product/" + mainCategory + "/" + subCategory + "/" + productCode + ".jpg";

        S3Util s3Util = new S3Util(awsS3Config.amazonS3Client(), awsS3Config.getBucket());
        String imageUrl = s3Util.getFileURL(fileName);

        return imageUrl;
    }

    private ProductInformationResponse findDetailedInformation(ProductDetails productDetails) {
        if (Objects.isNull(productDetails)) {
            return new ProductInformationResponse();
        }
        ProductInformationResponse productInformationResponse = ProductInformationResponse.builder()
            .saleUnit(productDetails.getSaleUnit())
            .weight(productDetails.getWeight())
            .deliveryClassification(productDetails.getDeliveryClassification())
            .packingType(productDetails.getPackingType())
            .originCountry(productDetails.getOriginCountry())
            .allergicReaction(productDetails.getAllergicReaction())
            .expirationDate(productDetails.getExpirationDate())
            .build();

        return productInformationResponse;
    }
}
