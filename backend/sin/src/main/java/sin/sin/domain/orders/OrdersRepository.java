package sin.sin.domain.orders;

import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import sin.sin.domain.orderProductList.QOrderProductList;
import sin.sin.domain.product.QProduct;
import sin.sin.domain.productCategory.QProductCategory;
import sin.sin.dto.order.OrdersDto;
import sin.sin.dto.order.OrdersProductDto;
import sin.sin.dto.order.OrdersResponse;
import sin.sin.dto.order.QOrdersDto;
import sin.sin.dto.order.QOrdersProductDto;
import sin.sin.util.ImgUtil;

@SuppressWarnings("unchecked")
@RequiredArgsConstructor
@Repository
public class OrdersRepository {

    private final JPAQueryFactory queryFactory;
    private final ImgUtil imgUtil;

    private QOrders orders = QOrders.orders;
    private QOrderProductList orderProductList = QOrderProductList.orderProductList;
    private QProduct product = QProduct.product;
    private QProductCategory productCategory = QProductCategory.productCategory;

    public List<OrdersResponse> findOrdersByMemberId(Long id) {
        List<OrdersProductDto> productDtoList = findOrdersThumbnailProduct(id);
        List<OrdersDto> ordersDtoList = findOrdersInfo(id);

        return makeOrdersResponse(productDtoList, ordersDtoList);
    }

    private List<OrdersProductDto> findOrdersThumbnailProduct(Long memberId) {
        return queryFactory.select(new QOrdersProductDto(
                product.id,
                product.productCode,
                product.name,
                product.productCategory.mainCategory,
                product.productCategory.subCategory
            )).from(orders)
            .join(orders.orderProductLists, orderProductList)
            .join(orderProductList.product, product)
            .where(orders.member.id.eq(memberId).and(
                orderProductList.id.in(JPAExpressions.select(orderProductList.id.max()).from(orders)
                    .join(orders.orderProductLists, orderProductList)
                    .where(orders.member.id.eq(memberId)).groupBy(orders.id))))
            .orderBy(orders.id.desc())
            .fetch();
    }

    private List<OrdersDto> findOrdersInfo(Long memberId) {
        return queryFactory.selectDistinct(new QOrdersDto(
                orders.id,
                orders.totalPrice,
                orders.orderStatus,
                orders.count()
            ))
            .from(orders)
            .where(orders.member.id.eq(memberId))
            .groupBy(orders.id)
            .orderBy(orders.id.desc())
            .fetch();
    }

    private List<OrdersResponse> makeOrdersResponse(List<OrdersProductDto> productDtoList,
        List<OrdersDto> ordersDtoList) {
        List<OrdersResponse> ordersResponses = new ArrayList<>();
        for (int i = 0; i < ordersDtoList.size(); i++) {
            ordersResponses.add(new OrdersResponse(
                ordersDtoList.get(i).getOrdersId(),
                ordersDtoList.get(i).getTotalPrice(),
                ordersDtoList.get(i).getOrderStatus(),
                productDtoList.get(i).getProductName(),
                ordersDtoList.get(i).getCount(),
                imgUtil.imgUrl(
                    productDtoList.get(i).getMainCategory(),
                    productDtoList.get(i).getSubCategory(),
                    productDtoList.get(i).getProductCode())
            ));
        }

        return ordersResponses;
    }
}
