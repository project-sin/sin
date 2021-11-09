package sin.sin.domain.cart;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import sin.sin.domain.product.QProduct;
import sin.sin.domain.productCategory.QProductCategory;
import sin.sin.dto.ProductFrame;
import sin.sin.dto.QProductFrame;
import sin.sin.dto.cart.MemberCartResponse;
import sin.sin.dto.cart.QMemberCartResponse;

import java.util.List;

@SuppressWarnings("unchecked")
@RequiredArgsConstructor
@Repository
public class SearchCartRepository {
    private final JPAQueryFactory queryFactory;
    private QCart cart = QCart.cart;
    private QProduct product = QProduct.product;
    private QProductCategory productCategory = QProductCategory.productCategory;

    public List<MemberCartResponse> findProductDetailByMemberId(Long id) {
        List<MemberCartResponse> memberCartResponse = queryFactory.select(new QMemberCartResponse(
                        product.name, product.productCode, product.price, productCategory, product.discountPercent, cart.count))
                .from(cart)
                .leftJoin(product).on(cart.product.eq(product))
                .leftJoin(productCategory).on(product.productCategory.eq(productCategory))
                .where(cart.member.id.eq(id)).fetch();

        return memberCartResponse;
    }

    public List<ProductFrame> findProductDetailByProductCode(List<String> productCodeList) {
        BooleanBuilder builder = new BooleanBuilder();
        for (String productCode :
                productCodeList) {
            builder.or(product.productCode.eq(productCode));
        }

        List<ProductFrame> nonMemberCartResponse = queryFactory.select(new QProductFrame(
                        product.name, product.productCode, product.price, productCategory, product.discountPercent))
                .from(product)
                .leftJoin(productCategory).on(product.productCategory.eq(productCategory))
                .where(builder).fetch();

        return nonMemberCartResponse;
    }

}
