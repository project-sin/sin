package sin.sin.domain.productCategory;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class ProductCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="product_category_id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String mainCategory;

    @Column(nullable = false)
    private String subCategory;
}
