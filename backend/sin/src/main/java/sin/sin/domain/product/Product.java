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
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import sin.sin.domain.productCatogory.ProductCatogory;

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
    @JoinColumn(name="product_category_id")
    private ProductCatogory productCategory;

    @Column(nullable = false)
    private int discountPercent;

    @CreationTimestamp
    private Timestamp createdDate;

    @NotNull
    @Enumerated(EnumType.STRING) // 이넘 이름을 DB에 저장
    private Status status;
}
