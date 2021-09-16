package sin.sin.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sin.sin.aws.AwsS3Config;
import sin.sin.aws.S3Util;
import sin.sin.domain.product.Product;
import sin.sin.domain.product.ProductRepository;
import sin.sin.domain.product.SearchProductRepository;
import sin.sin.dto.ProductListResponse;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Log4j2
@Service
public class ProductListService {
    private final SearchProductRepository searchProductRepository;
    private final AwsS3Config awsS3Config;

    @Transactional(readOnly = true)
    public List<ProductListResponse> newProductList() {
        //이번달 1일 00시 00분 00초
        SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-01 00:00:00.000");
        String today = format.format(System.currentTimeMillis());

        Timestamp ts = Timestamp.valueOf(today);
        log.info("이번달" + ts);

        List<ProductListResponse> products = searchProductRepository.findByCreatedDateGreaterThanEqual(ts);
        updateImgUrlProducts(products);

        return products;
    }

    @Transactional(readOnly = true)
    public List<ProductListResponse> bestProductList() {
        List<ProductListResponse> products = searchProductRepository.findTop20ByOrderByProductReviewCntDesc();
        updateImgUrlProducts(products);

        return products;
    }

    @Transactional(readOnly = true)
    public List<ProductListResponse> cheapProductList() {
        List<ProductListResponse> products = searchProductRepository.findTop30ByOrderByDiscountPercentDesc();
        updateImgUrlProducts(products);

        return products;
    }

    @Transactional(readOnly = true)
    public List<ProductListResponse> categoryProductList(String category) {
        List<ProductListResponse> products = searchProductRepository.findTop20ByOrderByCreatedDateDesc(category);
        updateImgUrlProducts(products);

        return products;
    }

    public void updateImgUrlProducts(List<ProductListResponse> products) {
        products.stream().forEach(product ->
                product.setImageUrl(imgUrl(product.getProductCategory().getMainCategory(), product.getProductCategory().getSubCategory(), product.getProductCode())));
    }

    public String imgUrl(String mainCategory, String subCategory, String productCode) {
        String fileName = "product/" + mainCategory + "/" + subCategory + "/" + productCode + ".jpg";

        S3Util s3Util = new S3Util(awsS3Config.amazonS3Client(), awsS3Config.getBucket());
        String imageUrl = s3Util.getFileURL(fileName);

        return imageUrl;
    }

}
