package sin.sin.domain.product;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import sin.sin.domain.productReview.QProductReview;
import sin.sin.dto.ProductListResponse;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class SearchProductRepository {
    private final JPAQueryFactory queryFactory;
    private QProduct product = QProduct.product;
    private QProductReview productReview = QProductReview.productReview;

    public List<ProductListResponse> findTop20ByOrderByProductReviewCntDesc() {
        List<ProductListResponse> results = selectFromJoin()
                .groupBy(product).orderBy(productReview.count().desc(), product.id.asc()).limit(20).fetch();

        return results;
    }

    public List<ProductListResponse> findByCreatedDateGreaterThanEqual(Timestamp thisMonth) {
        List<ProductListResponse> results = selectFromJoin()
                .where(product.createdDate.goe(thisMonth))
                .groupBy(product).orderBy(product.createdDate.desc(), product.id.asc()).limit(20).fetch();

        return results;
    }

    public List<ProductListResponse> findTop30ByOrderByDiscountPercentDesc() {
        List<ProductListResponse> results = selectFromJoin()
                .groupBy(product).orderBy(product.discountPercent.desc(), product.id.asc()).limit(30).fetch();

        return results;
    }

    public List<ProductListResponse> findTop20ByOrderByCreatedDateDesc(String category) {
        List<ProductListResponse> results = selectFromJoin()
                .where(product.productCategory.mainCategory.eq(category.substring(0, 3)))
                .groupBy(product).orderBy(product.createdDate.desc(), product.id.asc()).limit(30).fetch();

        return results;
    }

    JPAQuery<ProductListResponse> selectFromJoin() {
        return queryFactory.select(Projections.bean(ProductListResponse.class,
                        product.name, product.productCode, product.contentSummary, product.price, product.productCategory,
                        product.discountPercent, product.createdDate, product.status, productReview.count().intValue().as("reviewCount")))
                .from(product)
                .leftJoin(productReview).on(productReview.product.eq(product));
    }
}
