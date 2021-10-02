package sin.sin.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sin.sin.aws.AwsS3Config;
import sin.sin.aws.S3Util;
import sin.sin.domain.event.SearchEventRepository;
import sin.sin.domain.product.ProductRepository;
import sin.sin.domain.product.SearchMainRepository;
import sin.sin.dto.EventResponse;
import sin.sin.dto.ProductMain.MainCategoryResponse;
import sin.sin.dto.ProductMain.MainResponse;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MainService {

    private final SearchEventRepository searchEventRepository;
    private final SearchMainRepository searchMainRepository;
    private final ProductRepository productRepository;
    private final AwsS3Config awsS3Config;

    @Transactional
    public List<EventResponse> findMainBanner() {
        return searchEventRepository.findTop3ByOrderByCreatedDateDesc();
    }

    public List<MainResponse> findRecommendProduct() {
        return searchMainRepository.findTop8ByOrderByCreatedDate();
    }

    public List<MainResponse> findCheapProduct() {
        return searchMainRepository.findTop8ByOrderByDiscountPercentDesc();
    }

    public HashMap<String, List<MainResponse>> findMdChoice() {
        List<MainCategoryResponse> products = productRepository.findTop4ByOrderByProductReviewDesc();
        return MainCategoryResponseToHashAndMainResponse(products);
    }


    public HashMap<String, List<MainResponse>> MainCategoryResponseToHashAndMainResponse(List<MainCategoryResponse> products) {
        HashMap<String, List<MainResponse>> map = new HashMap<>();
        products.stream().forEach(product -> {
            map.putIfAbsent(product.getMainCategory(), new ArrayList<>());
            map.get(product.getMainCategory()).add(
                    MainResponse.builder()
                            .name(product.getName())
                            .productCode(product.getProductCode())
                            .imageUrl(imgUrl(product.getMainCategory(), product.getSubCategory(), product.getProductCode()))
                            .price(product.getPrice())
                            .discountPercent(product.getDiscountPercent())
                            .status(product.getStatus()).build());
                }
        );

        return map;
    }

    public String imgUrl(String mainCategory, String subCategory, String productCode) {
        String fileName = "product/" + mainCategory + "/" + subCategory + "/" + productCode + ".jpg";

        S3Util s3Util = new S3Util(awsS3Config.amazonS3Client(), awsS3Config.getBucket());
        String imageUrl = s3Util.getFileURL(fileName);

        return imageUrl;
    }
}

