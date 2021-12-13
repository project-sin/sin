package sin.sin.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sin.sin.domain.product.Product;
import sin.sin.domain.product.ProductRepository;
import sin.sin.domain.productQuestion.SearchProductQuestionRepository;
import sin.sin.dto.ProductDetails.ProductQnaResponse;

@Service
@RequiredArgsConstructor
public class ProductQnaService {
    private final ProductRepository productRepository;
    private final SearchProductQuestionRepository searchProductQuestionRepository;

    @Transactional
    public Page<ProductQnaResponse> findProductQna(String goodsNo, int page) {
        notExistedGoodsNoException(goodsNo);

        PageRequest pageable = PageRequest.of(page - 1, 10, Sort.by("createdDate").descending());

        return searchProductQuestionRepository.findProductQnaByProductCode(goodsNo, pageable);
    }

    private Product notExistedGoodsNoException(String goodsNo) {
        Product product = productRepository.findProductByProductCode(goodsNo)
                .orElseThrow(() ->
                        new IllegalArgumentException("goodsNo가 " + goodsNo + "에 해당되는 Product가 존재하지 않습니다."));
        return product;
    }
}
