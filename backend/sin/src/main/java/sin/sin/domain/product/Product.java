package sin.sin.domain.product;

import com.sun.istack.NotNull;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import sin.sin.domain.productCategory.ProductCategory;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="product_id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String productName;

    @Column(nullable = false)
    private String contentSummary;

    @Column(nullable = false)
    private String thumbnailName;

    @Column(nullable = false)
    private int price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "product_category_id")
    private ProductCategory productCategory;

    private float discountPercent;

    private String saleUnit;

    private String weight;

    private String deliveryClassification;

    private String packingType;

    private String originCountry;

    private String allergicReaction;

    private String expirationDate;

    @CreationTimestamp
    private Timestamp createdDate;

    @NotNull
    @Enumerated(EnumType.STRING) // 이넘 이름을 DB에 저장
    private Status status;
}
