package sin.sin.domain.product;

import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import sin.sin.domain.productCategory.ProductCategory;
import sin.sin.domain.productReview.ProductReview;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String productCode;

    private String contentSummary;

    @Column(nullable = false)
    private int price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "product_category_id")
    private ProductCategory productCategory;

    private int discountPercent;

    @Embedded
    private ProductDetails productDetails;

    @CreationTimestamp
    private Timestamp createdDate;

    @OneToMany(mappedBy="product", fetch=FetchType.LAZY)
    private List<ProductReview> productReview = new ArrayList<>();

    @NotNull
    @Enumerated(EnumType.STRING) // 이넘 이름을 DB에 저장
    private Status status;
}
