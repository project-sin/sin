package sin.sin.domain.productReviewImg;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
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
}
