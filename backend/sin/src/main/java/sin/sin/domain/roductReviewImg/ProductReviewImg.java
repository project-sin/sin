package sin.sin.domain.roductReviewImg;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sin.sin.domain.productReview.ProductReview;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ProductReviewImg {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="product_review_img_id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false, name = "product_review_id")
    private ProductReview productReview;

    @Column(nullable = false)
    private String fileName;

    @Lob
    @Column(nullable = false)
    private String filePath;
}
