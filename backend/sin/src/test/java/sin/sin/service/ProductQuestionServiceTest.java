package sin.sin.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;
import sin.sin.domain.member.Member;
import sin.sin.domain.member.MemberRepository;
import sin.sin.domain.product.Product;
import sin.sin.domain.product.ProductRepository;
import sin.sin.domain.productQuestion.ProductQuestionRepository;
import sin.sin.domain.productQuestion.Secret;
import sin.sin.dto.productQna.ProductQnaResponse;
import sin.sin.dto.productQna.ProductQuestionRequest;

import javax.persistence.EntityManager;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
class ProductQuestionServiceTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductQuestionService productQuestionService;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ProductQuestionRepository productQuestionRepository;

    @Autowired
    private EntityManager em;

    @Test
    void 상품qna_조회() {
        //given
        Product product = productRepository.findById(2L)
                .orElseThrow(() -> new IllegalArgumentException("해당되는 Product가 없습니다."));

        //when
        Page<ProductQnaResponse> qna = productQuestionService
                .findProductQna(product.getProductCode(), 1);

        //then
        assertThat(qna.getTotalElements()).isEqualTo(product.getProductQuestion().size());
    }

    @Test
    void 상품qna_생성() {
        //given
        Product product = productRepository.findById(2L)
                .orElseThrow(() -> new IllegalArgumentException("해당되는 Product가 없습니다."));

        ProductQuestionRequest request = ProductQuestionRequest.builder()
                .goodsNo(product.getProductCode())
                .title("111")
                .content("112121")
                .secret(Secret.Secret)
                .build();

        Member member = memberRepository.findById(1L).get();

        //when
        productQuestionService.insertProductQuestion(request, member);

        em.flush();
        em.clear();

        //then
        assertThat(productQuestionRepository.findByProduct(product).size()).isEqualTo(productQuestionRepository.findByProduct(product).size());

    }

}