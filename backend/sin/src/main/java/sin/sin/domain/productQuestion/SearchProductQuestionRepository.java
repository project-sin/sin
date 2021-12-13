package sin.sin.domain.productQuestion;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import sin.sin.domain.member.QMember;
import sin.sin.domain.product.QProduct;
import sin.sin.domain.productQuestionReply.QProductQuestionReply;
import sin.sin.domain.productReview.ProductReview;
import sin.sin.domain.productReview.QProductReview;
import sin.sin.domain.productReviewImg.ProductReviewImg;
import sin.sin.domain.productReviewImg.QProductReviewImg;
import sin.sin.dto.ProductDetails.*;
import sin.sin.util.ImgUtil;

import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("unchecked")
@RequiredArgsConstructor
@Repository
public class SearchProductQuestionRepository {
    private final JPAQueryFactory queryFactory;
    private QProductQuestion productQuestion = QProductQuestion.productQuestion;
    private QProductQuestionReply productQuestionReply = QProductQuestionReply.productQuestionReply;
    private QMember member = QMember.member;

    private QProduct product = QProduct.product;
    private QProductReview productReview = QProductReview.productReview;
    private QProductReviewImg productReviewImg = QProductReviewImg.productReviewImg;
    private final ImgUtil imgUtil;

    //productcode에 맞는 product_id를 가져오고 product_question을 pagination으로 가져오기
    public Page<ProductQnaResponse> findProductQnaByProductCode(String productCode, Pageable pageable) {
        JPAQuery<ProductQnaResponse> query = queryFactory.select(new QProductQnaResponse(
                        new QProductQuestionResponse(productQuestion.title, productQuestion.content, member.name, productQuestion.secret, productQuestion.createdDate),
                        new QProductQuestionReplyResponse(productQuestionReply.Content, productQuestionReply.createdDate)))
                .from(productQuestion)
                .leftJoin(member).on(productQuestion.member.eq(member))
                .leftJoin(productQuestionReply).on(productQuestionReply.productQuestion.eq(productQuestion))
                .where(productQuestion.product.productCode.eq(productCode))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());

        //정렬 쿼리
        for (Sort.Order o : pageable.getSort()) {
            PathBuilder pathBuilder = new PathBuilder(productQuestion.getType(), productQuestion.getMetadata());
            query.orderBy(new OrderSpecifier(o.isAscending() ? Order.ASC : Order.DESC,
                    pathBuilder.get(o.getProperty())));
        }

        List<ProductQnaResponse> result = query.fetch();

        return new PageImpl<>(result, pageable, result.size());
    }

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
                        .no(r.getId())
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
