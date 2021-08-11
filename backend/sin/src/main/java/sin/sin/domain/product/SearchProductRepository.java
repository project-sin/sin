package sin.sin.domain.product;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import sin.sin.domain.productReview.QProductReview;
import sin.sin.dto.ProductListResponse;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class SearchProductRepository {
    private final JPAQueryFactory queryFactory;
    private QProduct product = QProduct.product;
    private QProductReview productReview = QProductReview.productReview;

    public List<ProductListResponse> findTop20ByOrderByProductReviewDesc() {
        List<Tuple> tuple = queryFactory.select(product, productReview.count()).from(product)
                .leftJoin(productReview).on(productReview.product.eq(product))
                .groupBy(product).orderBy(productReview.count().desc(), product.id.asc()).limit(20).fetch();

        List<ProductListResponse> results = tuple.stream().map(r ->
                ProductListResponse.builder()
                        .id(r.get(product).getId())
                        .productCategory(r.get(product).getProductCategory())
                        .createdDate(r.get(product).getCreatedDate())
                        .discountPercent(r.get(product).getDiscountPercent())
                        .reviewCount(r.get(productReview.count()).intValue())
                        .status(r.get(product).getStatus())
                        .name(r.get(product).getName())
                        .price(r.get(product).getPrice())
                        .thumbnailName(r.get(product).getThumbnailName())
                        .thumbnailPath(r.get(product).getThumbnailPath())
                        .build()
        ).collect(Collectors.toList());

        return results;
    }
}
