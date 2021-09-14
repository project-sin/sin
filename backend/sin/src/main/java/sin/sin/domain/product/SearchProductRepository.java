package sin.sin.domain.product;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import sin.sin.domain.productReview.QProductReview;
import sin.sin.dto.ProductListResponse;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class SearchProductRepository {
    private final JPAQueryFactory queryFactory;
    private QProduct product = QProduct.product;
    private QProductReview productReview = QProductReview.productReview;

    public List<ProductListResponse> findTop20ByOrderByProductReviewCntDesc() {
        List<ProductListResponse> results = queryFactory.select(Projections.bean(ProductListResponse.class,
                        product.name, product.productCode, product.price, product.productCategory,
                        product.discountPercent, product.createdDate, product.status, productReview.count().intValue().as("reviewCount")))
                .from(product)
                .leftJoin(productReview).on(productReview.product.eq(product))
                .groupBy(product).orderBy(productReview.count().desc(), product.id.asc()).limit(20).fetch();

        return results;
    }
}
