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
import sin.sin.domain.productQuestionReply.QProductQuestionReply;
import sin.sin.dto.ProductDetails.ProductQnaResponse;
import sin.sin.dto.ProductDetails.QProductQnaResponse;
import sin.sin.dto.ProductDetails.QProductQuestionReplyResponse;
import sin.sin.dto.ProductDetails.QProductQuestionResponse;

import java.util.List;

@SuppressWarnings("unchecked")
@RequiredArgsConstructor
@Repository
public class SearchProductQuestionRepository {
    private final JPAQueryFactory queryFactory;
    private QProductQuestion productQuestion = QProductQuestion.productQuestion;
    private QProductQuestionReply productQuestionReply = QProductQuestionReply.productQuestionReply;
    private QMember member = QMember.member;

    //productcode에 맞는 product_id를 가져오고 product_question을 pagination으로 가져오기
    public Page<ProductQnaResponse> findByProductCode(String productCode, Pageable pageable) {
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
}
