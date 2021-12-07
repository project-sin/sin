package sin.sin.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sin.sin.domain.product.SearchProductRepository;
import sin.sin.dto.ProductListResponse;
import sin.sin.util.ImgUtil;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

@RequiredArgsConstructor
@Log4j2
@Service
public class ProductListService {
    private final SearchProductRepository searchProductRepository;
    private final ImgUtil imgUtil;

    @Transactional(readOnly = true)
    public List<ProductListResponse> newProductList() {
        //이번달 1일 00시 00분 00초
        SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-01 00:00:00.000");
        String today = format.format(System.currentTimeMillis());

        Timestamp ts = Timestamp.valueOf(today);
        log.info("이번달" + ts);

        List<ProductListResponse> products = searchProductRepository.findByCreatedDateGreaterThanEqual(ts);
        imgUtil.updateImgUrlProducts(products);

        return products;
    }

    @Transactional(readOnly = true)
    public List<ProductListResponse> bestProductList() {
        List<ProductListResponse> products = searchProductRepository.findTop20ByOrderByProductReviewCntDesc();
        imgUtil.updateImgUrlProducts(products);

        return products;
    }

    @Transactional(readOnly = true)
    public List<ProductListResponse> cheapProductList() {
        List<ProductListResponse> products = searchProductRepository.findTop30ByOrderByDiscountPercentDesc();
        imgUtil.updateImgUrlProducts(products);

        return products;
    }

    @Transactional(readOnly = true)
    public List<ProductListResponse> categoryProductList(String category) {
        List<ProductListResponse> products = searchProductRepository.findTop20ByOrderByCreatedDateDesc(category);
        imgUtil.updateImgUrlProducts(products);

        return products;
    }

    @Transactional(readOnly = true)
    public List<ProductListResponse> searchProductList(String sword) {
        List<ProductListResponse> products = searchProductRepository.findByName(sword);
        imgUtil.updateImgUrlProducts(products);

        return products;
    }
}


