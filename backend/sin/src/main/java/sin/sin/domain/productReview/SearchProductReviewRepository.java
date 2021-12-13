package sin.sin.domain.productReview;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import sin.sin.domain.member.QMember;
import sin.sin.domain.product.QProduct;
import sin.sin.domain.productReviewImg.ProductReviewImg;
import sin.sin.domain.productReviewImg.QProductReviewImg;
import sin.sin.dto.ProductReviewResponse;
import sin.sin.util.ImgUtil;

import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("unchecked")
@RequiredArgsConstructor
@Repository
public class SearchProductReviewRepository {
    private final JPAQueryFactory queryFactory;
    private QProduct product = QProduct.product;
    private QProductReview productReview = QProductReview.productReview;
    private QProductReviewImg productReviewImg = QProductReviewImg.productReviewImg;
    private final ImgUtil imgUtil;
    private QMember member = QMember.member;

    public Page<ProductReviewResponse> findProductReviewByProductCode(String productCode, Pageable pageable) {
        List<ProductReview> review = queryFactory.selectFrom(productReview)
                .leftJoin(productReview.product, product).fetchJoin()
                .leftJoin(productReview.member, member).fetchJoin()
                .leftJoin(productReview.productReviewImgList, productReviewImg).fetchJoin()
                .where(productReview.product.productCode.eq(productCode))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(productReview.createdDate.desc())
                .fetch();

        List<ProductReviewResponse> result = review.stream()
                .map(r -> ProductReviewResponse.builder()
                        .id(r.getId())
                        .title(r.getTitle())
                        .memberName(r.getMember().getName())
                        .createdDate(r.getCreatedDate())
                        .likeCnt(r.getLikeCnt())
                        .views(r.getViews())
                        .productName(r.getProduct().getName())
                        .productImg(imgList(r.getProductReviewImgList()))
                        .content(r.getContent())
                        .build())
                .collect(Collectors.toList());

        return new PageImpl<>(result, pageable, result.size());
    }

    //img url string 리스트로 만들어서 return 하기
    private List<String> imgList(List<ProductReviewImg> productReviewImgList) {
        return productReviewImgList.stream().map(r -> imgUtil.imgUrl(r.getFilePath(), r.getFileName())).collect(Collectors.toList());
    }
}
