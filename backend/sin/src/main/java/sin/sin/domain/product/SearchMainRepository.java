package sin.sin.domain.product;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import sin.sin.aws.AwsS3Config;
import sin.sin.aws.S3Util;
import sin.sin.domain.productReview.QProductReview;
import sin.sin.dto.MainResponse;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class SearchMainRepository {
    private final JPAQueryFactory queryFactory;
    private final AwsS3Config awsS3Config;
    private QProduct product = QProduct.product;
    private QProductReview productReview = QProductReview.productReview;

    public List<MainResponse> findTop8ByOrderByCreatedDate() {
        List<Product> products = queryFactory.selectFrom(product)
                .orderBy(product.createdDate.asc())
                .limit(8).fetch();

        return productsToMainResponse(products);
    }

    public List<MainResponse> findTop8ByOrderByDiscountPercentDesc() {
        List<Product> products = queryFactory.selectFrom(product)
                .orderBy(product.discountPercent.desc())
                .limit(8).fetch();

        return productsToMainResponse(products);
    }

    public List<MainResponse> productsToMainResponse(List<Product> products) {
        List<MainResponse> mainResponse = products.stream().map(product ->
                MainResponse.builder()
                        .name(product.getName())
                        .productCode(product.getProductCode())
                        .imageUrl(imgUrl(product.getProductCategory().getMainCategory(), product.getProductCategory().getSubCategory(), product.getProductCode()))
                        .price(product.getPrice())
                        .discountPercent(product.getDiscountPercent())
                        .status(product.getStatus()).build()).collect(Collectors.toList());

        return mainResponse;
    }

    public String imgUrl(String mainCategory, String subCategory, String productCode) {
        String fileName = "product/" + mainCategory + "/" + subCategory + "/" + productCode + ".jpg";

        S3Util s3Util = new S3Util(awsS3Config.amazonS3Client(), awsS3Config.getBucket());
        String imageUrl = s3Util.getFileURL(fileName);

        return imageUrl;
    }

}
