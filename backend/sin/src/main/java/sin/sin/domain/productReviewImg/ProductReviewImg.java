package sin.sin.domain.productReviewImg;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import sin.sin.domain.productReview.ProductReview;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "productReview")
public class ProductReviewImg {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="product_review_img_id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String fileName;

    @Lob
    @Column(nullable = false)
    private String filePath;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "product_review_id")
    private ProductReview productReview;

}
