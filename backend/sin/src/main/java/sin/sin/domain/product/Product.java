package sin.sin.domain.product;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import sin.sin.domain.productCategory.ProductCategory;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="product_id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    //TODO : 주석 풀기

//    @Column(nullable = false)
    private String thumbnailName;

//    @Column(nullable = false)
    private String thumbnailPath;

    @Column(nullable = false)
    private int price;

    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(nullable = false, name = "product_category_id")
    @JoinColumn(name = "product_category_id")
    private ProductCategory productCategory;

    @Column(nullable = false)
    private int discountPercent;

    @CreationTimestamp
    private Timestamp createdDate;

    @NotNull
    @Enumerated(EnumType.STRING) // 이넘 이름을 DB에 저장
    private Status status;
}
