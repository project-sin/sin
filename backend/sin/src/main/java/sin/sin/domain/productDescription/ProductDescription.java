package sin.sin.domain.productDescription;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sin.sin.domain.product.Product;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ProductDescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="product_description_id", nullable = false)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "product_id")
    private Product product;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String fileName;

    @Column(nullable = false)
    private String filePath;
}
