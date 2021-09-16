package sin.sin.domain.product;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findProductByProductCode(String productCode);
}
