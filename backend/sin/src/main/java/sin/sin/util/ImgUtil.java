package sin.sin.util;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import sin.sin.aws.AwsS3Config;
import sin.sin.aws.S3Util;
import sin.sin.dto.ProductFrame;

import java.util.List;

@RequiredArgsConstructor
@Component
public class ImgUtil {

    private final AwsS3Config awsS3Config;

    public void updateImgUrlProducts(List<? extends ProductFrame> products) {
        products.stream().forEach(product ->
                product.setImageUrl(imgUrl(product.getProductCategory().getMainCategory(), product.getProductCategory().getSubCategory(), product.getProductCode())));
    }

    public String imgUrl(String mainCategory, String subCategory, String productCode) {
        String fullName = "product/" + mainCategory + "/" + subCategory + "/" + productCode + ".jpg";

        S3Util s3Util = new S3Util(awsS3Config.amazonS3Client(), awsS3Config.getBucket());
        String imageUrl = s3Util.getFileURL(fullName);

        return imageUrl;
    }

    public String imgUrl(String filePath, String fileName) {
        String fullName = filePath + "/" + fileName;

        S3Util s3Util = new S3Util(awsS3Config.amazonS3Client(), awsS3Config.getBucket());
        String imageUrl = s3Util.getFileURL(fullName);

        return imageUrl;
    }

}
