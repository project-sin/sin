package sin.sin.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sin.sin.domain.product.Product;
import sin.sin.domain.product.ProductRepository;
import sin.sin.domain.productReview.SearchProductReviewRepository;
import sin.sin.dto.ProductReviewResponse;

@Service
@RequiredArgsConstructor
public class ProductReviewService {
    private final ProductRepository productRepository;
    private final SearchProductReviewRepository searchProductReviewRepository;

    @Transactional
    public Page<ProductReviewResponse> findProductReview(String goodsNo, int page) {
        Product product = notExistedGoodsNoException(goodsNo);

        PageRequest pageable = PageRequest.of(page - 1, 10, Sort.by("createdDate").descending());

        return searchProductReviewRepository.findProductReviewByProductCode(goodsNo, pageable);
    }

    private Product notExistedGoodsNoException(String goodsNo) {
        Product product = productRepository.findProductByProductCode(goodsNo)
                .orElseThrow(() ->
                        new IllegalArgumentException("goodsNo가 " + goodsNo + "에 해당되는 Product가 존재하지 않습니다."));
        return product;
    }
}
