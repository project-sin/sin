package sin.sin.domain.productQuestion;

import org.springframework.data.jpa.repository.JpaRepository;
import sin.sin.domain.product.Product;

import java.util.List;

public interface ProductQuestionRepository extends JpaRepository<ProductQuestion, Long> {
    List<ProductQuestion> findByProduct(Product product);
}
