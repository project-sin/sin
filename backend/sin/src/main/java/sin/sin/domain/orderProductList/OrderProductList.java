package sin.sin.domain.orderProductList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sin.sin.domain.orders.Orders;
import sin.sin.domain.product.Product;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class OrderProductList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="order_product_list_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "order_id")
    private Orders orders;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "product_id")
    private Product product;

    @Column(nullable = false)
    private int count;
}
