package sin.sin.domain.orders;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import sin.sin.domain.orderProductList.QOrderProductList;
import sin.sin.domain.product.Product;
import sin.sin.domain.product.QProduct;
import sin.sin.domain.productCategory.QProductCategory;
import sin.sin.dto.order.OrderProductsResponse;
import sin.sin.dto.order.OrdersDto;
import sin.sin.dto.order.OrdersProductDto;
import sin.sin.dto.order.OrdersResponse;
import sin.sin.dto.order.QOrderProductsResponse;
import sin.sin.dto.order.QOrdersDto;
import sin.sin.dto.order.QOrdersProductDto;
import sin.sin.util.ImgUtil;

@SuppressWarnings("unchecked")
@RequiredArgsConstructor
@Repository
public class OrdersRepository {

    private final JPAQueryFactory queryFactory;

    private QOrders orders = QOrders.orders;
    private QOrderProductList orderProductList = QOrderProductList.orderProductList;
    private QProduct product = QProduct.product;
    private QProductCategory productCategory = QProductCategory.productCategory;

    public boolean checkMember(Long memberId, Long orderId) {
        Integer fetchOne = queryFactory.selectOne()
            .from(orders)
            .where(orders.id.eq(orderId).and(orders.member.id.eq(memberId)))
            .fetchFirst();

        return fetchOne != null; //exist 와 동일한 효과, but exists() 를 쓸때보다 성능 향상
    }


    public List<OrderProductsResponse> findProductsByOrdersId(Long id) {
        List<OrderProductsResponse> responses = queryFactory.select(new QOrderProductsResponse(
                product.name,
                product.productCode,
                product.price,
                product.productCategory,
                product.discountPercent,
                orderProductList.count
            )).from(orders)
            .join(orders.orderProductLists, orderProductList)
            .join(orderProductList.product, product)
            .where(orders.id.eq(id))
            .orderBy(orderProductList.id.desc())
            .fetch();

        return responses;
    }

    public List<Orders> findOrdersByMemberId(Long id) {
        List<Orders> orderList = queryFactory.selectDistinct(orders)
            .from(orders)
            .join(orders.orderProductLists, orderProductList)
            .fetchJoin()
            .join(orderProductList.product, product)
            .fetchJoin()
            .join(product.productCategory, productCategory)
            .fetchJoin()
            .where(orders.member.id.eq(id))
            .orderBy(orders.id.desc())
            .fetch();

        return orderList;
    }
}
