package sin.sin.domain.product;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import sin.sin.domain.productCatogory.ProductCatogory;

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
    private String name;

    @Column(nullable = false)
    private String thumbnailName;

    @Column(nullable = false)
    private String thumbnailPath;

    @Column(nullable = false)
    private float price;

    @ManyToOne
    @JoinColumn(nullable = false, name = "product_category_id")
    private ProductCatogory productCategory;

    @Column(nullable = false)
    private float discountPercent;

    @CreationTimestamp
    private Timestamp createdDate;
}
