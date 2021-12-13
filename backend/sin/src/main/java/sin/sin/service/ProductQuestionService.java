package sin.sin.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sin.sin.domain.member.Member;
import sin.sin.domain.product.Product;
import sin.sin.domain.product.ProductRepository;
import sin.sin.domain.productQuestion.ProductQuestion;
import sin.sin.domain.productQuestion.ProductQuestionRepository;
import sin.sin.domain.productQuestion.SearchProductQuestionRepository;
import sin.sin.dto.productQna.ProductQnaResponse;
import sin.sin.dto.productQna.ProductQuestionRequest;

@Service
@RequiredArgsConstructor
public class ProductQuestionService {
    private final ProductRepository productRepository;
    private final SearchProductQuestionRepository searchProductQuestionRepository;
    private final ProductQuestionRepository productQuestionRepository;

    @Transactional(readOnly = true)
    public Page<ProductQnaResponse> findProductQna(String goodsNo, int page) {
        notExistedGoodsNoException(goodsNo);

        PageRequest pageable = PageRequest.of(page - 1, 10, Sort.by("createdDate").descending());

        return searchProductQuestionRepository.findProductQnaByProductCode(goodsNo, pageable);
    }

    @Transactional
    public void insertProductQuestion(ProductQuestionRequest productQuestionRequest, Member member) {
        Product product = notExistedGoodsNoException(productQuestionRequest.getGoodsNo());

        ProductQuestion productQuestion = ProductQuestion.builder()
                .product(product)
                .content(productQuestionRequest.getContent())
                .member(member)
                .secret(productQuestionRequest.getSecret())
                .title(productQuestionRequest.getTitle())
                .build();

        productQuestionRepository.save(productQuestion);
        product.getProductQuestion().add(productQuestion); //member가 영속성이 아니니 추가
    }

    private Product notExistedGoodsNoException(String goodsNo) {
        Product product = productRepository.findProductByProductCode(goodsNo)
                .orElseThrow(() ->
                        new IllegalArgumentException("goodsNo가 " + goodsNo + "에 해당되는 Product가 존재하지 않습니다."));
        return product;
    }
}
